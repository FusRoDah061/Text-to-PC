package com.example.texttopc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class Client extends AsyncTask<Void, Void, Void>{

	//Endereço IP do servidor
	private String serverIPAddress;
	//Porta que o servidor está escutando
	private int serverPort;
	private String dados;
	private String TAG = "TextToPC";
	private Context context;
	
	public Client(String ip, int port, Context context,String dados){
		serverIPAddress = ip;
		serverPort = port;
		this.context = context;
		this.dados = dados;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		Socket socket = null;
		
		try{
			//Cria um socket que busca o server pelo ip e porta
			socket = new Socket(serverIPAddress,serverPort);
			
			ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream(1024);
			
			byte[] data = dados.getBytes();
			
			mByteArrayOutputStream.write(data, 0, data.length);

		}catch(UnknownHostException e){
			Log.i(TAG, "Client: UnknownHostException: " + e.getMessage());
			//Toast.makeText(context, "Host desconhecido", Toast.LENGTH_SHORT).show();
		}catch(IOException e){
			Log.i(TAG, "Client: IOException: " + e.getMessage());
			//Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			Log.i(TAG, "Client: Exception: " + e.getMessage());
			//Toast.makeText(context, "Erro ao enviar os dados", Toast.LENGTH_SHORT).show();
		}finally{
			if(socket != null){
				try{
					socket.close();
				}catch(IOException e){
					Log.i(TAG, "Client: IOException: " + e.getMessage());
				}
			}
		}
		
		return null;

	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		//Toast.makeText(context, "Dados enviados", Toast.LENGTH_SHORT).show();
		super.onPostExecute(result);
	}
	

}
