package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFOpener extends AppCompatActivity {

    PDFView myPDFViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfopener);

        myPDFViewer=(PDFView) findViewById(R.id.pdfViewer);

        String getItem = getIntent().getStringExtra("pdfFileName");

        if (getItem.equals("French")){
            myPDFViewer.fromAsset("french.pdf").load();
        }
        if (getItem.equals("Geography")){
            myPDFViewer.fromAsset("geography.pdf").load();
        }
        if (getItem.equals("History")){
            myPDFViewer.fromAsset("history.pdf").load();
        }
        if (getItem.equals("Math")){
            myPDFViewer.fromAsset("math.pdf").load();
        }
        if (getItem.equals("SVT")){
            myPDFViewer.fromAsset("svt.pdf").load();
        }


    }
}


