package com.example.texttopc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonEnviar;
	private EditText editTextDados;
	private EditText editTextServer;
	private EditText editTextPort;
	private String TAG = "TextToPC";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        buttonEnviar = (Button)findViewById(R.id.buttonEnviar);
        editTextDados = (EditText)findViewById(R.id.editTextDados);
        editTextServer = (EditText)findViewById(R.id.editTextServer);
        editTextPort = (EditText)findViewById(R.id.editTextPort);
        
        buttonEnviar.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(buttonEnviar)){
			//Segue para enviar os dados
			String IPaddress = editTextServer.getText().toString(); 
			if(IPaddress == ""){
				IPaddress = "192.168.0.186";
			}
			
			int port;
			
			try{
				port = Integer.parseInt(editTextPort.getText().toString());
			}catch(NullPointerException e){
				Log.i(TAG,"Activity: NullPointerException: " + e.getMessage());
				port = 5004;
			}catch(Exception e){
				Log.i(TAG,"Activity: Exception: " + e.getMessage());
				port = 5004;
				Log.i(TAG,"Activity: Porta: " + String.valueOf(port));
			}

			String dados = editTextDados.getText().toString();
			if(dados == ""){
				dados = "Sem dados";
			}
			
			Client mClient = new Client(IPaddress,port,this,dados);
			mClient.execute();
		}
	}
}
