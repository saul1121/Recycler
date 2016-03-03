package com.mobileappscompany.training.recycler;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by admin on 2/27/2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>{


    private List<Person> people;

    public PeopleAdapter(List<Person> people) {
        this.people = people;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        TextView mPersonNames;
        TextView mPersonStatus;
        ImageView mPhoto;
        TextView mButtonShare;
        TextView mDate;


        public PersonViewHolder(final View itemView) {
            super(itemView);

            //set a button with action listener por each cardview
            mButtonShare = (TextView) itemView.findViewById(R.id.shareButton);
            mButtonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String msg = ((TextView) itemView.findViewById(R.id.name)).getText().toString() + " .- " + ((TextView) itemView.findViewById(R.id.status)).getText().toString();

                    intent.putExtra(Intent.EXTRA_TEXT, msg);
                    v.getContext().startActivity(intent);
                    //content to share

                }
            });


            //change*****************
            mPersonNames = (TextView) itemView.findViewById(R.id.name);
            mPersonStatus = (TextView) itemView.findViewById(R.id.status);
            mPhoto = (ImageView) itemView.findViewById(R.id.photo);
            mDate = (TextView) itemView.findViewById(R.id.date);



        }
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //create a new view from the cardview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_view, parent, false);

        PersonViewHolder personViewHolder = new PersonViewHolder(view);

        return personViewHolder;

    }

    @Override
    public void onBindViewHolder(PersonViewHolder holderPerson, int position) {

        holderPerson.mPhoto.setImageResource(people.get(position).getPhotoId());
        holderPerson.mPersonStatus.setText(people.get(position).getStatus());
        holderPerson.mPersonNames.setText(people.get(position).getFullName());
        holderPerson.mDate.setText(people.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return people.size();
    }




}
