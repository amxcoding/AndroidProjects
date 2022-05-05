package com.example.alarmemina.Settings.AlarmSound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.alarm.R;
import java.util.List;

public class SoundListAdapter extends ArrayAdapter<Sound> {
    Context mContext;
    int mResource;
    TextView songNameTv;
    RadioButton radioButton;

    public SoundListAdapter(Context context, int resource, List<Sound> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        songNameTv = convertView.findViewById(R.id.optionName);
        Sound sound = getItem(position);
        songNameTv.setText(sound.GetName());

        radioButton = convertView.findViewById(R.id.radioButton);
        radioButton.setChecked(sound.IsChecked());
        radioButton.setOnCheckedChangeListener(new OnCheckedChangedListener(this, position));

        return convertView;
    }

    class OnCheckedChangedListener implements RadioButton.OnCheckedChangeListener{
        int position;
        SoundListAdapter adapter;

        OnCheckedChangedListener(SoundListAdapter soundListAdapter, int pos){
            adapter = soundListAdapter;
            position = pos;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                UncheckAllExcept(position);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void UncheckAllExcept(int position){
        for (int i = 0; i < getCount(); i++){
             if(i == position)
                getItem(i).SetChecked(true);
             else
                getItem(i).SetChecked(false);
        }
    }
}
