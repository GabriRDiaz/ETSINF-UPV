public class Pool1 extends Pool {
    int kidS = 0;
    int monit = 0;

    public synchronized void init(int ki, int cap){}
    public synchronized void kidSwims() throws InterruptedException{
        while(monit<1){
            log.waitingToSwim();
            wait();
        }
        kidS++;
        log.swimming();
        notifyAll();
    }
    public synchronized void kidRests(){
        log.resting(); 
        kidS--;
        notifyAll();
    }
    public synchronized void instructorSwims(){
        log.swimming();
        monit++;
    }
    public synchronized void instructorRests() throws InterruptedException{
        while(kidS>0 && monit==1){
            log.waitingToRest();
            wait();
        }
        monit--;
        log.resting();
        notifyAll();    
    }
}
