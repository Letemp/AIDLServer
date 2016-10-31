package book.chapter05.aidlserver;

import java.util.Timer;
import java.util.TimerTask;

import book.chapter05.aidlserver.Song.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLServer extends Service {

	private String[] names=new String[]{"老男孩","春天里","在路上"};
	private String[] authors=new String[]{"筷子兄弟","汪峰","刘欢"};
	private String name,author;
	private SongBinder songBinder;
	private Timer timer=new Timer();
	
	public class SongBinder extends Stub{
		public String getName()throws RemoteException{
			return name;
		}

		@Override
		public String getAuthor() throws RemoteException {
			// TODO Auto-generated method stub
			return author;
		}
	}
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return songBinder;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		songBinder=new SongBinder();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int rand=(int)(Math.random()*3);
				name=names[rand];
				author=authors[rand];
				
			}
		},1000);
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}
}
