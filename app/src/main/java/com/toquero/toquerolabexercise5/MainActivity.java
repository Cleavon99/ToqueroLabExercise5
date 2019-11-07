package com.toquero.toquerolabexercise5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] api, ver, level, date, description;
    ListView list;
    private File file;
    private File folder;
    int[] cLogo = {R.drawable.cupcake, R.drawable.donut, R.drawable.eclair, R.drawable.froyo, R.drawable.gingerbread,
            R.drawable.honeycomb, R.drawable.icecream, R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop,
            R.drawable.marshmallow, R.drawable.nougat, R.drawable.oreo, R.drawable.pie, R.drawable.android10};

    ArrayList<Version> VersionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = getResources().getStringArray(R.array.apis);
        ver = getResources().getStringArray(R.array.versions);
        level = getResources().getStringArray(R.array.level);
        date = getResources().getStringArray(R.array.relDate);
        description = getResources().getStringArray(R.array.description);
        list = findViewById(R.id.lvVersions);
        for (int i = 0; i < api.length; i++) {
            VersionList.add(new Version(cLogo[i], api[i], ver[i], level[i], date[i], description[i]));
        }
        list = findViewById(R.id.lvVersions);
        VersionAdapter adapter = new VersionAdapter(this, R.layout.item, VersionList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int i, long id) {

        folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        file = new File(folder, "AndroidVersions.txt");
        try {

            FileOutputStream fos = new FileOutputStream(file);
            String choiceApi = VersionList.get(i).getApis();
            fos.write(choiceApi.getBytes());
            String choiceReleaseDate = "\n" + VersionList.get(i).getRelDate();
            fos.write(choiceReleaseDate.getBytes());
            String choiceVersions = "\n" + VersionList.get(i).getVersions();
            fos.write(choiceVersions.getBytes());
            String choiceDesc = "\n" + VersionList.get(i).getDescription();
            fos.write(choiceDesc.getBytes());
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(VersionList.get(i).getApis());
            dialog.setIcon(VersionList.get(i).getLogo());
//            final String api = VersionList.get(i).getApis();
//            final String ver = VersionList.get(i).getVersions();
//            final String date = VersionList.get(i).getRelDate();
            dialog.setMessage(VersionList.get(i).getDescription());
            dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String readData = readInput();
                    Toast.makeText(MainActivity.this, readData, Toast.LENGTH_LONG).show();
                }
            });
            dialog.create().show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String readInput() {
        FileInputStream stream = null;
        file = new File(folder, "AndroidVersions.txt");
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader oBuffer = new BufferedReader(new FileReader(file));
            String i;
            int count = 0;
            while ((i = oBuffer.readLine()) != null) {
                count++;
                sb.append(i + " \n");
                if (count == 2) {
                    break;
                }
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

