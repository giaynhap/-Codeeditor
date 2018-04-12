package com.example.imac.coderediter.UI;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.imac.coderediter.R;
import com.example.imac.coderediter.Tree.Keyworld;
import com.example.imac.coderediter.Tree.TernaryTree;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class EditorControl extends AutoCompleteTextView {
    private EditText editDisplay;
    private EditText rowDisplay;
    private Activity context;
    private int rowColor;
    private int rowBgColor;
    private PopupWindow popup;
    private String typed = "";
    private ListView viewkeyword;
    TernaryTree <Keyworld>Tree = new TernaryTree<Keyworld>();

    private ColorSchemeManager colorScheme = new ColorSchemeManager();
    String[] arr = { "Paries,France", "PA,United States","Parana,Brazil",
            "Padua,Italy", "Pasadena,CA,United States"};

    public EditorControl(Context context, AttributeSet attrs) {
        super(context, attrs);

        Tree.Add(new Keyworld("console","console"));
        Tree.Add(new Keyworld("console.log",".log()"));
        Tree.Add(new Keyworld("console.assert",".assert()"));
        Tree.Add(new Keyworld("console.clear",".clear()"));
        Tree.Add(new Keyworld("console.error",".error()"));
        Tree.Add(new Keyworld("console.info",".info()"));
        Tree.Add(new Keyworld("console.group",".group()"));

        Tree.Add(new Keyworld("Math","Math"));
        Tree.Add(new Keyworld("Math.PI","PI"));

        Tree.Add(new Keyworld("Math.round",".round()"));
        Tree.Add(new Keyworld("Math.pow",".pow()"));
        Tree.Add(new Keyworld("Math.sqrt",".sqrt()"));
        Tree.Add(new Keyworld("Math.abs",".abs()"));
        Tree.Add(new Keyworld("Math.floor",".floor()"));
        Tree.Add(new Keyworld("Math.sin",".sin()"));

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EditorControl,
                0, 0);

        try {
            rowColor = a.getColor(R.styleable.EditorControl_rowColor,Color.WHITE);
            rowBgColor = a.getInteger(R.styleable.EditorControl_rowBgColor, Color.BLACK);
        } finally {
            a.recycle();
        }


        this.setPadding(110,0,0,0);
        this.getBackground().clearColorFilter();
        this.addTextChangedListener( new EventTextChange());
        this.setBackgroundColor(Color.parseColor("#384550"));
        this.setTextColor(Color.WHITE);
        this.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        this.setSingleLine(false);


    }
    class EventTextChange implements TextWatcher

    {

        private String getWordFromChar(int index,String text)
        {
            if (index>=text.length() ) index-=1;
            int curr = index;
            char[] array = text.toCharArray();
            String str = "";
            if (curr >0 )
            {
               while (true)
               {

                   if (curr<0|| array[curr]==' ' || array[curr]=='\n'){
                       break;
                   }
                   curr--;
               }
                if (index>=text.length() ) index-=1;
               for(int i=curr+1;i<=index;i++){
                   str+=array[i];
               }
            }
            return str;

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            int characterOffset = i ;
            Layout layout = EditorControl.this.getLayout();
            if (layout==null) return;
            int line = layout.getLineForOffset(characterOffset);
            float  mCursorX = layout.getPrimaryHorizontal(characterOffset);
            float mCursorBaseY = layout.getLineBaseline(line);
            float mCursorAscentY = layout.getLineAscent(line);
            if (popup!=null) {
                setPosition((int) mCursorX, (int) (mCursorBaseY + mCursorAscentY + 270));

            }
            else{
                showPopup((Activity) EditorControl.this.getContext(),0,270);
            }
            String word = this.getWordFromChar(i,s.toString());

            if ( word.trim().length()>0)
            {

                ArrayList<Keyworld> tree = Tree.getListFromNode(word.trim());

                if (tree.size()>0){
                    ArrayList<String> tree2 = new ArrayList<>();
                    for (Keyworld key :tree)
                    {
                        tree2.add(key.getmDisplay());
                    }

                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(  EditorControl.this.getContext()  ,android.R.layout.simple_list_item_1,  tree2 );
                viewkeyword.setAdapter(arrayAdapter);}
                else
                EditorControl.this.closePopup();


            }
            else {
                EditorControl.this.closePopup();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            colorScheme.textChanged(s);

        }
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Rect rect = new Rect();
        this.getLocalVisibleRect(rect);
        int baseline = getBaseline();
        Paint paint=new Paint();
        Rect rect_numBar = new Rect(0,0,105,rect.bottom);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.rowBgColor);
        canvas.drawRect(rect_numBar, paint);
        paint.setColor(this.rowColor);
        paint.setTextSize(30f);
        for (int i = 0; i < getLineCount(); i++) {
            String str = "" + (i+1);
            Rect bounds = new Rect();
            paint.getTextBounds(str, 0, str.length(), bounds);
            canvas.drawText(str, rect.left + 90-bounds.right, baseline, paint);
            baseline += getLineHeight();
        }
    }

    private void showPopup(final Activity context, int a,int b) {
        int popupWidth = 500;
        int popupHeight = 400;


        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, null);


        popup = new PopupWindow(context);


        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(false);
        int OFFSET_X = 30;
        int OFFSET_Y = 30;
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, a + OFFSET_X,b + OFFSET_Y);

           viewkeyword = layout.findViewById(R.id.viewkeyword);


    }
    public void setPosition(int x, int y)
    {
        int OFFSET_X = 30;
        int OFFSET_Y = 30;
        popup.update(OFFSET_X+x,y+OFFSET_Y,500,400);




    }
    public void closePopup(){
        if (popup==null) return ;
        popup.dismiss();
        popup = null;
    }



}

