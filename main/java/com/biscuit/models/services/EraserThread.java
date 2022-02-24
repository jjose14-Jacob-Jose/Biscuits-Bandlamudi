package com.biscuit.models.services;

class EraserThread implements Runnable {
	   private boolean stop;

	   public EraserThread(String prompt) {
	       System.out.print(prompt);
	   }

	   @SuppressWarnings("static-access")
	public void run () {
	      while (!stop){
	         System.out.print("\010*");
	         try {
	            Thread.currentThread().sleep(1);
	         } catch(InterruptedException ie) {
	            ie.printStackTrace();
	         }
	      }
	   }

	   public void stopMasking() {
	      this.stop = true;
	   }
	}
