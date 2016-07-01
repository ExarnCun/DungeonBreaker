package world;

public abstract class World {

	
	

	/**
	 * The Thread used for Ticking
	 */
	public Runnable TickThread = new Runnable(){

		public boolean active;
		
		@Override
		public void run() {
			while(active){
				
			}
		}
		
	};
	
	
}
