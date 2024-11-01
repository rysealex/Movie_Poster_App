// Alex Ryse
package com.example.movieposterapp;

// imports
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// MainActivity class
public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchList; // declaring the button for adding to watchlist

    /**
     * onCreate method overridden, initialized essential components of application.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize a new RecyclerView
        RecyclerView posterRecyclerView = findViewById(R.id.postersRecyclerView);
        // define the buttonAddToWatchList button
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchList);

        // prepare the movie poster data, ArrayList posterList holds the movie posters (Poster)
        List<Poster> posterList = new ArrayList<>();

        // create 10 posters, each with the image, name, createdBy, rating, and story variables
        Poster poster1 = new Poster(); // first poster
        poster1.image = R.drawable.overthehedge;
        poster1.name = "Over The Hedge";
        poster1.createdBy = "Tim Johnson and Karey Kirkpatrick";
        poster1.rating = 5f;
        poster1.story = "A crazy squirrel and a mastermind racoon";
        posterList.add(poster1); // add to posterList

        Poster poster2 = new Poster(); // second poster
        poster2.image = R.drawable.beemovie;
        poster2.name = "Bee Movie";
        poster2.createdBy = "Steve Hickner and Simon J. Smith";
        poster2.rating = 4f;
        poster2.story = "A bee goes to court";
        posterList.add(poster2); // add to posterList

        Poster poster3 = new Poster(); // third poster
        poster3.image = R.drawable.monsterinc;
        poster3.name = "Monsters, Inc.";
        poster3.createdBy = "Pete Docter";
        poster3.rating = 3f;
        poster3.story = "Monsters who scare children";
        posterList.add(poster3); // add to posterList

        Poster poster4 = new Poster(); // fourth poster
        poster4.image = R.drawable.iceage;
        poster4.name = "Ice Age";
        poster4.createdBy = "Michael J. Wilson";
        poster4.rating = 4f;
        poster4.story = "A group of prehistoric mammals try to survive the ice age";
        posterList.add(poster4); // add to posterList

        Poster poster5 = new Poster(); // fifth poster
        poster5.image = R.drawable.thepolarexpress;
        poster5.name = "The Polar Express";
        poster5.createdBy = "Robert Zemeckis";
        poster5.rating = 4f;
        poster5.story = "Children take a train to the North Pole";
        posterList.add(poster5); // add to posterList

        Poster poster6 = new Poster(); // sixth poster
        poster6.image = R.drawable.howtotrainyourdragon;
        poster6.name = "How To Train Your Dragon";
        poster6.createdBy = "Chris Sanders and Dean DeBlois";
        poster6.rating = 4f;
        poster6.story = "A viking trains a dragon";
        posterList.add(poster6); // add to posterList

        Poster poster7 = new Poster(); // seventh poster
        poster7.image = R.drawable.cloudywithachanceofmeatballs;
        poster7.name = "Cloudy with a Chance of Meatballs";
        poster7.createdBy = "Phil Lord and Chris Miller";
        poster7.rating = 4f;
        poster7.story = "An inventor makes it rain food";
        posterList.add(poster7); // add to posterList

        Poster poster8 = new Poster(); // eighth poster
        poster8.image = R.drawable.shrek;
        poster8.name = "Shrek";
        poster8.createdBy = "Andrew Adamson and Vicky Jenson";
        poster8.rating = 4f;
        poster8.story = "An ogre and donkey go on an adventure";
        posterList.add(poster8); // add to posterList

        Poster poster9 = new Poster(); // ninth poster
        poster9.image = R.drawable.madagascar;
        poster9.name = "Madagascar";
        poster9.createdBy = "Tom McGrath and Eric Darnell";
        poster9.rating = 3f;
        poster9.story = "A lion, zebra, hippo, and giraffe escape a zoo";
        posterList.add(poster9); // add to posterList

        Poster poster10 = new Poster(); // tenth poster
        poster10.image = R.drawable.bolt;
        poster10.name = "Bolt";
        poster10.createdBy = "Bryan Howard and Chris Williams";
        poster10.rating = 4f;
        poster10.story = "A TV star dog believes he has superpowers";
        posterList.add(poster10); // add to posterList

        // initialize a new PosterAdapter for this posterList
        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        // set the posterAdapter to the RecyclerView
        posterRecyclerView.setAdapter(posterAdapter);

        // set an OnClickListener to the buttonAddToWatchList
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method overridden, creates a Toast pop up notification when movie poster view clicked.
             * Toast pop up notification details the name of the movie poster(s) clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // initialize selectedPosters
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                // create a StringBuilder posterNames, to hold the movie poster names
                StringBuilder posterNames = new StringBuilder();
                // iterate through each selectedPoster and append each name to posterNames
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                // create the Toast pop up notification using the posterNames StringBuilder
                Toast.makeText(MainActivity.this,posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * onPosterAction method overridden, decides whether to display the buttonToAddWatchList.
     * Only display if user selects a movie poster, else not displayed.
     *
     * @param isSelected Boolean flag to check if a movie poster is selected.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected) {
            buttonAddToWatchList.setVisibility(View.VISIBLE); // display the button
        } else {
            buttonAddToWatchList.setVisibility(View.GONE); // don't display the button
        }
    }
}