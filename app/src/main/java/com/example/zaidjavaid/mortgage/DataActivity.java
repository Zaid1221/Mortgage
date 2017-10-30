package com.example.zaidjavaid.mortgage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class DataActivity extends AppCompatActivity {
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data );
        updateView( );
    }

    public void updateView( ) {
        Mortgage mortgage = MainActivity.mortgage; //shares the same mortgage object from main activity to allow passing of data
        if( mortgage.getYears( ) == 10 ) {  //these set which button will be pressed by default when the activity is shown by checking the data from mortagage class
            RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
            rb10.setChecked( true );
        } else if( mortgage.getYears( ) == 15 ) {
            RadioButton rb15 = ( RadioButton ) findViewById( R.id.fifteen );
            rb15.setChecked( true );
        } // else do nothing (default is 30)

        EditText amountET = ( EditText ) findViewById( R.id.data_amount );  //these do the same as the buttons from above
        amountET.setText( "" + mortgage.getAmount( ) );
        EditText rateET = ( EditText ) findViewById( R.id.data_rate );
        rateET.setText( "" + mortgage.getRate( ) );
    }

    public void updateMortgageObject( ) { //updates all the data from the text fields
        Mortgage mortgage = MainActivity.mortgage;
        RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
        RadioButton rb15 = ( RadioButton ) findViewById( R.id.fifteen );
        int years = 30;
        if( rb10.isChecked( ) ) //check which button is pressed
            years = 10;
        else if( rb15.isChecked( ) )
            years = 15;
        mortgage.setYears( years ); //set the years to that amount the button was choosen for

        EditText amountET = ( EditText ) findViewById( R.id.data_amount );
        String amountString = amountET.getText( ).toString( ); //set the value of the field to a string varaible
        EditText rateET = ( EditText ) findViewById( R.id.data_rate );
        String rateString = rateET.getText( ).toString( ); //set the value of the field to a string varaible

        try {     //parses the strings to floats to be stored into the mortgage object
            float amount = Float.parseFloat( amountString );
            mortgage.setAmount( amount );
            float rate = Float.parseFloat( rateString );
            mortgage.setRate( rate );
            mortgage.setPreferences( this ); //calls the preference to store the data for next time the app is run
        } catch( NumberFormatException nfe ) {
            mortgage.setAmount( 100000.0f );
            mortgage.setRate( .035f );
        }
    }

    public void goBack( View v ) { //button to be pressed to go back to main screen to display new data
        updateMortgageObject( );
        this.finish( );
        overridePendingTransition( R.anim.fade_in_and_scale, 0 );
    }
}
