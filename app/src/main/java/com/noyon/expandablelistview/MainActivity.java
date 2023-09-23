package com.noyon.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lView;
    HashMap<String,String> map;
    ArrayList<HashMap<String,String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding id's
        lView=findViewById(R.id.lView);


        //call method for add value
        addInformation();


        MyAdapter adapter = new MyAdapter();
        lView.setAdapter(adapter);



    }


    //==================================   adapter class    =====================

     private class MyAdapter extends BaseAdapter{


         @Override
         public int getCount() {
             return list.size();
         }

         @Override
         public Object getItem(int i) {
             return null;
         }

         @Override
         public long getItemId(int i) {
             return 0;
         }

         @Override
         public View getView(int i, View view, ViewGroup viewGroup) {

               LayoutInflater layInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View myView= layInflater.inflate(R.layout.item,viewGroup,false);

             //finding id's
             CardView cardView=myView.findViewById(R.id.cardView);
             LinearLayout layDes= myView.findViewById(R.id.layDes);
             LinearLayout motherLay=myView.findViewById(R.id.motherLay);
             ImageView imgArrow= myView.findViewById(R.id.imgArrow);
             ImageView itemImg=myView.findViewById(R.id.itemImg);
             TextView tvTitle= myView.findViewById(R.id.tvTitle);
             TextView tvDes= myView.findViewById(R.id.tvDes);


             //get hashmap from arraylist
             HashMap<String,String> map= list.get(i);

             //get string from hasmap
             String Title= map.get("Title");
             String Des= map.get("Des");
             String ImageString= map.get("Image");


             //convert imageString into int so that it can be display in imageView

             int Image=Integer.parseInt(ImageString);


             //display all things im item

             tvTitle.setText(Title);
             tvDes.setText(Des);
             itemImg.setImageResource(Image);







             //click event
             cardView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     

                  if(layDes.getVisibility()==View.GONE){

                      //Check if any other layDes expended  or not if expended then close it
                      for(int x=0 ; x<lView.getChildCount(); x++){

                          View newView= lView.getChildAt(x);
                          //now child layout are connected to newView so it is possible to find Id's through newView
                          LinearLayout newDes=newView.findViewById(R.id.layDes);
                          ImageView newArrowImg= newView.findViewById(R.id.imgArrow);
                          LinearLayout newMotherLay= newView.findViewById(R.id.motherLay);

                          //now handle everyThing
                          newDes.setVisibility(View.GONE);
                          newArrowImg.setImageResource(R.drawable.uparraw);
                          newMotherLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

                      }

                      //if item visivility gone execute this part

                      layDes.setVisibility(View.VISIBLE);
                      imgArrow.setImageResource(R.drawable.downarraw);

                      motherLay.setBackgroundColor(Color.parseColor("#724CAF50"));
                      TransitionManager.beginDelayedTransition(motherLay,new AutoTransition());



                  }

                  else{

                      layDes.setVisibility(View.GONE);
                      imgArrow.setImageResource(R.drawable.uparraw);
                      TransitionManager.beginDelayedTransition(motherLay,new AutoTransition());
                      motherLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

                  }




                 }
             });






             return myView;
         }
     }



    //===============================  class end===============================

    //======================= user defined method=================

    private void addInformation(){

        map=new HashMap<>();
        map.put("Title","This is title 1") ;
        map.put("Des"," this is title 1 description.Dummy text : Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis");
        map.put("Image",Integer.toString(R.drawable.shoe));
        list.add(map);

        map=new HashMap<>();
        map.put("Title","This is title 2");
        map.put("Image",Integer.toString(R.drawable.makeup));
        map.put("Des","This is title 2 description. Dummy text Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis");
        list.add(map);


        map= new HashMap<>();
        map.put("Title","This is title 3");
        map.put("Des","This is title 3 description. Dummyyy text Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis");
        map.put("Image",Integer.toString(R.drawable.piano));
        list.add(map);

        map= new HashMap<>();
        map.put("Title","This  is title 4 ");
        map.put("Des","This is title 4 description . Dummy text : Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis");
        map.put("Image",Integer.toString(R.drawable.unknown));
        list.add(map);




    }

    //============================end====================



}