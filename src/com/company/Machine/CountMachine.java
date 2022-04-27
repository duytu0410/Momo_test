package com.company.Machine;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class CountMachine {
    private CountMachine(){}
    private static class SingletonHelper{
        private static final CountMachine INSTANCE = new CountMachine();
    }
    public static CountMachine getInstance(){
        return SingletonHelper.INSTANCE;
    }
    static long interval = 86400;
    static Timer timer;
    //Hàm tính thời gian từ hiện tại tới 24h ngày hôm đó

    public void countDown(CheckMachine checkMachine,CalculateMachine calcMachine,InitMachine initMachine){
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval(checkMachine,calcMachine,initMachine);
            }
        }, delay, period);
    }
    private static void setInterval(CheckMachine checkMachine,CalculateMachine calculateMachine, InitMachine initMachine) {
        --interval;
//        //Sang ngày tiếp theo
        if(checkMachine.checkIfADayPassed(interval)){
            interval = calculateMachine.calculateTimeToNextDay();
            if(checkMachine.budgetIsEmpty(initMachine.getBudget())){
                initMachine.setInit();
            }else if(!checkMachine.budgetIsEmpty(initMachine.getBudget())){
               initMachine.increaseWinRate();
            }
        }

    }

}
