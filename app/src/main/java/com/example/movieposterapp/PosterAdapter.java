// Alex Ryse
package com.example.movieposterapp;

// imports
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

// PosterAdapter class
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{

    /**
     * onCreateViewHolder method overridden, from RecyclerView.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return PosterViewHolder, containing the item_container_poster layout.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * onBindViewHolder method overridden, from RecyclerView.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * getItemCount method, overridden from RecyclerView.
     *
     * @return int, the number of posters in posterList.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    // declare ArrayList of type Poster for posterList
    private List<Poster> posterList;

    /**
     * PosterAdapter constructor to initialize the posterList ArrayList and postersListener PostersListener.
     *
     * @param posterList The posterList of movie posters that are displayed in the program.
     *
     * @param postersListener The postersListener that is used in the program.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    // declare PostersListener for the movie posters
    private PostersListener postersListener;

    /**
     * getSelectedPosters method, adds all the selected movie posters to the selectedPosters list.
     *
     * @return The ArrayList of type Poster selectedPosters.
     */
    public List<Poster> getSelectedPosters() {
        // initialize selectedPosters to hold the movie posters
        List<Poster> selectedPosters = new ArrayList<>();
        // iterate through each movie poster and add to selectedPosters list if the user has clicked on
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters; // return the ArrayList of selected movie posters
    }

    // PosterViewHolder class
    class PosterViewHolder extends RecyclerView.ViewHolder {

        // declare the different components of the movie poster view
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * PosterViewHolder method, initializes all the components of the movie poster view.
         *
         * @param itemView The current display that is being displayed in the program.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView); // get current itemView

            // initialize each key component from the current itemView
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * bindPosters method, sets the key components for each movie poster.
         * Determines whether a user has clicked on a movie poster.
         * Selected movie posters will display a checkmark graphic and
         * change background color to signify this.
         *
         * @param poster The current movie poster in the app.
         */
        void bindPosters(final Poster poster) {
            // set the image, name, createdBy, and ratingBar components of the current movie poster
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            // determine if user clicked on the movie poster
            if (poster.isSelected) {
                // displays a checkmark graphic to signify this
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                // un-display the checkmark graphic
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            // enable an onClickListener to the layoutPosters
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                /**
                 * onClick method overridden, determines whether a movie poster is selected again by the user.
                 * Selecting a movie poster again will un-display the checkmark graphic to signify
                 * that the current movie poster is not selected. (back to original view)
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    // if a movie poster if already selected, update background and un-display the checkmark
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false; // set the isSelected method to false (no selected movie posters)
                        if (getSelectedPosters().isEmpty()) {
                            // turn off onPosterAction
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        // not already selected movie poster, display background and checkmark
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true; // set the isSelected method to true (selected movie posters)
                        // turn on onPosterAction
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
