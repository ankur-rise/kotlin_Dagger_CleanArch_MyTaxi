package com.ankur.mytaxi.ui.adapter;

import android.content.Context;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankur.mytaxi.R;
import com.ankur.mytaxi.network.GeocoderHelper;
import com.ankur.mytaxi.util.AppUtils;
import com.ankur.mytaxi.viewmodel.Coordinate;
import com.ankur.mytaxi.viewmodel.PoiList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleRecyclerAdapter extends RecyclerView.Adapter<VehicleRecyclerAdapter.CustomViewHolder> {
    private List<PoiList> list;
    private Geocoder geocoder;
    private ItemClickListener listener;
    private LruCache<String, String> cache;

    public VehicleRecyclerAdapter(ArrayList<PoiList> list, Geocoder geocoder, ItemClickListener listener) {
        this.list = list;
        this.geocoder = geocoder;
        this.listener = listener;
        cache = new LruCache<>(10);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(R.layout.list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        PoiList poi = list.get(position);
        final Coordinate coord = poi.getCoordinate();
        holder.tvCurrentLoc.setText("lat-" + String.format("%.2f", coord.getLatitude()) + " " + "lon-" + String.format("%.2f", coord.getLongitude()));
        getAddress(coord.getLatitude(), coord.getLongitude(), new GeocoderHelper.GeocoderAddressListener() {
            @Override
            public void onSuccess(String address) {
                if (cache.get(coord.getLatitude() + "-" + coord.getLongitude()) == null) {
                    cache.put(coord.getLatitude() + "-" + coord.getLongitude(), address);
                }
                holder.tvCurrentLoc.setText(address);
            }
        });

        holder.tvDestination.setText(AppUtils.getDirection(holder.tvDestination.getContext(), poi.getHeading()));
        holder.tvFleet.setText(poi.getFleetType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void getAddress(double lat, double longitude, GeocoderHelper.GeocoderAddressListener listener) {
        String address = cache.get(lat + "-" + longitude);
        if (address != null && !address.isEmpty()) {
            listener.onSuccess(address);
            return;
        }
        GeocoderHelper geoasync = new GeocoderHelper(geocoder, listener);
        geoasync.execute(lat, longitude);
//        String address = "";
//        List<Address> addresses;
//
//        try {
//            addresses = geocoder.getFromLocation(lat, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            address = addresses.get(0).getAddressLine(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return address;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_currentlocation)
        TextView tvCurrentLoc;
        @BindView(R.id.tv_destination)
        TextView tvDestination;
        @BindView(R.id.tv_fleettype)
        TextView tvFleet;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
