"""
Author:  CAnalytica
Start Date: Jan 11 2020
End Date: Jan 12 2020
Description: Social Media Review Analysis Service
"""
"________Importing Libraries___________"
import nltk
import tweepy
from nltk.stem.porter import PorterStemmer
from nltk.corpus import stopwords
import csv
import firebase_admin
nltk.download('stopwords')
stop_words = stopwords.words('english')
from nltk.stem.wordnet import WordNetLemmatizer
lem = WordNetLemmatizer()
stem = PorterStemmer()
import pandas as pd
nltk.download('punkt')
import re, string
stop_words = stopwords.words('english')
import random
from firebase_admin import credentials, firestore
from nltk import classify
from nltk.tag import pos_tag
from nltk.corpus import twitter_samples
nltk.download('wordnet')
nltk.download('averaged_perceptron_tagger')
from nltk.stem.wordnet import WordNetLemmatizer
from nltk import FreqDist
from nltk import NaiveBayesClassifier
from nltk.tokenize import word_tokenize
"_________END____________"


"________API CREDENTIALS___________"
"Twitter API User credentials"
consumer_key = "HBb2EdO1Rn8zSiGHS1j49rNvd"
consumer_secret = "MkhGPXcP6sxLgM5iXqA4jheYKeg5RwYXqWTPw1ncOybzylGroB"
access_token = "973025558006185984-RKIUNbjzWODFrbuj5VaMWG7u5cbMf9G"
access_token_secret = "xwpSNs67Y945OgpasZsSDrU1p9KBCYn7e8Vo76Q1RWLi2"

"FireStore User Credentials"
cred = credentials.Certificate("rbcanalytica-firebase-adminsdk-mjex8-bef3e1c772.json")
firebase_admin.initialize_app(cred)
db = firestore.client()
"_________END____________"

auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth,wait_on_rate_limit=True)
# Open/Create a file to append data
tweetFile = open('tweet.csv', 'a')
tweetWriter = csv.writer(tweetFile)

train_tweets = pd.read_csv('train_tweets.csv')
test_tweets = pd.read_csv('test_tweets.csv')

positive_tweets = twitter_samples.strings('positive_tweets.json')
negative_tweets = twitter_samples.strings('negative_tweets.json')
text = "Jatt is Jatt fuck you RBC no matter what"
tweet_tokens = twitter_samples.tokenized('positive_tweets.json')
tweet_tokens = twitter_samples.tokenized('positive_tweets.json')

def lemmatize_sentence(tokens):
    lemmatizer = WordNetLemmatizer()
    lemmatized_sentence = []
    for word, tag in pos_tag(tokens):
        if tag.startswith('NN'):
            pos = 'n'
        elif tag.startswith('VB'):
            pos = 'v'
        else:
            pos = 'a'
        lemmatized_sentence.append(lemmatizer.lemmatize(word, pos))
    return lemmatized_sentence

def remove_noise(tweet_tokens, stop_words = ()):

    cleaned_tokens = []

    for token, tag in pos_tag(tweet_tokens):
        token = re.sub('http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+#]|[!*\(\),]|'\
                       '(?:%[0-9a-fA-F][0-9a-fA-F]))+','', token)
        token = re.sub("(@[A-Za-z0-9_]+)","", token)

        if tag.startswith("NN"):
            pos = 'n'
        elif tag.startswith('VB'):
            pos = 'v'
        else:
            pos = 'a'

        lemmatizer = WordNetLemmatizer()
        token = lemmatizer.lemmatize(token, pos)

        if len(token) > 0 and token not in string.punctuation and token.lower() not in stop_words:
            cleaned_tokens.append(token.lower())
    return cleaned_tokens

positive_tweet_tokens = twitter_samples.tokenized('positive_tweets.json')
negative_tweet_tokens = twitter_samples.tokenized('negative_tweets.json')

positive_cleaned_tokens_list = []
negative_cleaned_tokens_list = []

for tokens in positive_tweet_tokens:
    positive_cleaned_tokens_list.append(remove_noise(tokens, stop_words))

for tokens in negative_tweet_tokens:
    negative_cleaned_tokens_list.append(remove_noise(tokens, stop_words))

def get_all_words(cleaned_tokens_list):
    for tokens in cleaned_tokens_list:
        for token in tokens:
            yield token

all_pos_words = get_all_words(positive_cleaned_tokens_list)
freq_dist_pos = FreqDist(all_pos_words)

def get_tweets_for_model(cleaned_tokens_list):
    for tweet_tokens in cleaned_tokens_list:
        yield dict([token, True] for token in tweet_tokens)

positive_tokens_for_model = get_tweets_for_model(positive_cleaned_tokens_list)
negative_tokens_for_model = get_tweets_for_model(negative_cleaned_tokens_list)

positive_dataset = [(tweet_dict, "P")
                     for tweet_dict in positive_tokens_for_model]

negative_dataset = [(tweet_dict, "N")
                     for tweet_dict in negative_tokens_for_model]

dataset = positive_dataset + negative_dataset

random.shuffle(dataset)
random.shuffle(dataset)
train_data = dataset[:7000]
test_data = dataset[7000:]

classifier = NaiveBayesClassifier.train(train_data)


technicalWords=[]
productList=[]

while True:

    doc_ref = db.collection(u"application").document(u'zK4OS4dCy8XXvFDP2jAV')
    "Creating a Thead to be Posted"
    application = doc_ref.get().to_dict()
    threadName = application['threadName']
    #api.update_status(threadName)
    HashtagName = application['hashTagName']
    print(HashtagName, threadName)
    print("Accuracy is:", classify.accuracy(classifier, test_data))

    "Collecting and Analyzing the tweets"
    technicalFlag = False;
    for tweet in tweepy.Cursor(api.search, q=HashtagName, count=10, lang="en", since="2019-01-10").items(5):
        custom_tweet = tweet.text
        custom_tokens = remove_noise(word_tokenize(custom_tweet))
        print(tweet.text)
        print(classifier.classify(dict([token, True] for token in custom_tokens)))
        if (for token in custom_tokens) == technicalWords:
            technicalFlag = True;
#        elif(for token in custom_tokens) == productList:
        if classifier.classify(dict([token, True] for token in custom_tokens)) == "P":
            positiveFlag, = True;
        elif classifier.classify(dict([token, True] for token in custom_tokens)) == "N":
            positiveFlag = False;

        doc_ref = db.collection(u"twitter").add({
            u'Date': tweet.created_at,
            u'phrase': tweet.text,
            u'positive': positiveFlag,
            u'technical': technicalFlag
        })
        tweetWriter.writerow([tweet.created_at, tweet.text.encode('utf-8')])
