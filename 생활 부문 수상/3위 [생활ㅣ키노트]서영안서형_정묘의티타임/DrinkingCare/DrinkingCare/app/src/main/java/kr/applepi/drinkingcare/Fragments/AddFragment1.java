package kr.applepi.drinkingcare.Fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.applepi.drinkingcare.Adapters.RecyclerAdapter;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.Product;

/**
 * Created by user on 2015-12-19.
 */
public class AddFragment1 extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add1, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_add_recyclerview);

        List<Product> data = new ArrayList<>();
        Product p1 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_water), "물");
        Product p2 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_soda),"탄산음료");
        Product p3 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_ion),"이온음료");
        Product p4 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_coffee),"커피");
        Product p5 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_tea),"차");
        Product p6 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_energy),"에너지 드링크");
        Product p7 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_juice),"주스");
        Product p8 = new Product((BitmapDrawable) getResources().getDrawable(R.drawable.icon_cup),"기타");

        data.add(p1);
        data.add(p2);
        data.add(p3);
        data.add(p4);
        data.add(p5);
        data.add(p6);
        data.add(p7);
        data.add(p8);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);

        return rootView;
    }
}
