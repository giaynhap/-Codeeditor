package com.example.imac.coderediter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.imac.coderediter.Tree.TernaryTree;
import com.example.imac.coderediter.UI.FileDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        /*
        TernaryTree Tree = new TernaryTree();
        Tree.Add("Hello");
        Tree.Add("Bug");
        Tree.Add("cat");
        Tree.Add("Cats");
        Tree.Add("Catser");


       // System.out.println("Hel"+Tree.SearchTST("Hel"));
       // System.out.println("Cats"+Tree.SearchTST("Cats"));
       // System.out.println("allo"+Tree.SearchTST("allo"));
       // Tree.traverseTSTUtil(Tree.SearchTST("Ca"),"");

        ArrayList<String> strs = Tree.getListFromNode("Cat");
        for ( String a:strs)
        {
            System.out.println(a);
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_save) {
            FileDialog FileSaveDialog =  new FileDialog(MainActivity.this, "FileSave",
                    new FileDialog.SimpleFileDialogListener()
                    {
                        @Override
                        public void onChosenDir(String chosenDir)
                        {

                        }
                    });


            FileSaveDialog.Default_File_Name = "my_default.txt";
            FileSaveDialog.chooseFile_or_Dir();

            return true;
        }else if (id == R.id.action_run)
        {
            Intent intent=  new Intent(MainActivity.this,Console.class);

           startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
