package com.android.rbcanalytica.ui.postslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.rbcanalytica.R
import com.android.rbcanalytica.repository.Review
import com.google.android.material.chip.Chip

class PostsListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PostsListAdapter.ReviewViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var reviews = emptyList<Review>() // Cached copy of words

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postItemContent: TextView = itemView.findViewById(R.id.postContent)
        val postItemPositive: Chip = itemView.findViewById(R.id.positiveChip)
        val postItemTechnical: Chip = itemView.findViewById(R.id.technicalChip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = inflater.inflate(R.layout.post_item, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val post = reviews[position]
        holder.postItemContent.text = post.reviewText
        if(post.positive){
            holder.postItemPositive.text = "Positive"
        }else{
            holder.postItemPositive.text = "Negative"
        }

        if(post.technical){
            holder.postItemTechnical.text = "Technical"
        }else{
            holder.postItemTechnical.text = "Non Technical"
        }
    }

    internal fun setReviews(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

    override fun getItemCount() = reviews.size
}
