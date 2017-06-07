package com.blup.android.blup.adaptateritem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blup.android.blup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String userId;

    public List getItemsList() {
        return itemsList;
    }

    public MyAdapter setItemsList(List itemsList) {
        this.itemsList = itemsList;

        return this;
    }

    private List itemsList;

    public String getUserId() {
        return userId;
    }

    public MyAdapter setUserId(String userId) {
        this.userId = userId;

        return this;
    }



    private final List<Pair<String, String>> characters = Arrays.asList(
           Pair.create("Lyra Belacqua", "Brave, curious, and crafty, she has been prophesied by the witches to help the balance of life"),
           Pair.create("Pantalaimon", "Lyra's daemon, nicknamed Pan."),
           Pair.create("Roger Parslow", "Lyra's friends"),
           Pair.create("Lord Asriel", "Lyra's uncle"),
           Pair.create("Marisa Coulter", "Intelligent and beautiful, but extremely ruthless and callous."),
           Pair.create("Iorek Byrnison", "Armoured bear, Rightful king of the panserbjørne. Reduced to a slave of the human village Trollesund."),
           Pair.create("Serafina Pekkala", "Witch who closely follows Lyra on her travels."),
           Pair.create("Lee Scoresby", "Texan aeronaut who transports Lyra in his balloon. Good friend with Iorek Byrnison."),
           Pair.create("Ma Costa", "Gyptian woman whose son, Billy Costa is abducted by the \"Gobblers\"."),
           Pair.create("John Faa", "The King of all gyptian people.")
   );



   @Override
   public int getItemCount() {
       return characters.size();
   }

   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.inflate(R.layout.list_home, parent, false);
       return new MyViewHolder(view);
   }

   @Override
   public void onBindViewHolder(MyViewHolder holder, int position) {
       Pair<String, String> pair = characters.get(position);
       holder.display(pair);
   }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       private final TextView name;
       private final TextView description;

       private Pair<String, String> currentPair;

       public MyViewHolder(final View itemView) {
           super(itemView);

           name = ((TextView) itemView.findViewById(R.id.title));
           description = ((TextView) itemView.findViewById(R.id.date));

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   new AlertDialog.Builder(itemView.getContext())
                           .setTitle(currentPair.first)
                           .setMessage(currentPair.second)
                           .show();
               }
           });
       }

       public void display(Pair<String, String> pair) {
           currentPair = pair;
           name.setText(pair.first);
           description.setText(pair.second);
       }
   }

}