/**
	 * Send a message through a udp connection
	 * @param Send a message through a udp connection
	 * @param name message
	 * @param type String
	 */
	@SuppressLint("NewApi")
	public void	SendUDP(final String message){
		asyncUdpClient = new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... params) {   
                DatagramSocket ds = null;
                try{
                	ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(params[0]), Integer.parseInt(params[1]));
                  	ds.setBroadcast(serverActive);
                    ds.send(dp);
                } catch (Exception e)  {
                   e.printStackTrace();
                } finally { 
                    if (ds != null){ds.close();}
                }
                return null;
            }

            @Override
			protected void onPostExecute(Void result) {
               super.onPostExecute(result);
               asyncUdpClient = null;
            }
        };

        if (Build.VERSION.SDK_INT >= 11) asyncUdpClient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ipAddress, portClient+"");
        else asyncUdpClient.execute( ipAddress, portClient+"");
    }



	/**
	 * Send a message through a tcp connection 
	 * @param Send a message through a tcp connection
	 * @param name message
	 * @param type String
	 * @throws IOException
	 */
	public void SendTCP(String message) throws IOException{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socketClient.getOutputStream())),true);
		out.println(message); 
	} 
	