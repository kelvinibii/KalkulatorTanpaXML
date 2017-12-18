package tech.web.kelvinibii.kalkulatortanpaxml;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Tugas kalkulator WT3
 * Anggota kelompok:
 * -Kelvin - 56140275
 * -Harfi  - 55140131
 * -Jemmy  - 52140071
 * -Mikkel - 50140035
 * -Samuel - 57140122
 *
 * Created by KelvinDu on 12/12/2017.
 */

public class KalkulatorGue extends AppCompatActivity {
    public static final int EDIT_ID = 99;
    Button buttons[] = new Button[18];
    String symbols[] = {"1","2","3","4","5","6","7","8","9","0","C","CE","+","-","*","/","%","="};
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(relativeLayout);
        RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editParams.setMargins(10,10,10,10);
        EditText editText = new EditText(this);
        editText.setId(EDIT_ID);
        relativeLayout.addView(editText, editParams);
        ButtonGue buttonGue = new ButtonGueImpl(buttons, symbols, editText, this);
        buttonGue.addButtonToView(relativeLayout);
    }
}
