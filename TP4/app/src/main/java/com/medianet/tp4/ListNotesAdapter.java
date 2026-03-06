package com.medianet.tp4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medianet.tp4.Model.StudentModel;

import java.util.ArrayList;

public class ListNotesAdapter extends ArrayAdapter<Double> {
    private final Context context;
    private final StudentModel student;

    public ListNotesAdapter(@NonNull Context context, StudentModel student) {
        super(context, R.layout.note_item, new ArrayList<>(student.getData().values()));
        this.context = context;
        this.student = student;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        }


        ImageView emojiView = convertView.findViewById(R.id.img_view_emoji);
        TextView noteView  = convertView.findViewById(R.id.tv_note);

        //double note = student.get(position).getData().values();
        //noteView.setText(String.valueOf(note));
        //emojiView.setImageResource((note>=10) ? R.mipmap.sourire : R.mipmap.triste);

        double note = getItem(position);
        Log.e("note in Adapter", position+" "+note);
        noteView.setText(String.valueOf(note));
        emojiView.setImageResource(note >= 10 ? R.mipmap.sourire : R.mipmap.triste);

        return convertView;
    }
}
