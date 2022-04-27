package com.company.Machine;

public class MainMachine {

    public static void main(String[] args) {
        InitMachine initMachine = InitMachine.getInstance();
        DisplayMachine displayMachine = DisplayMachine.getInstance();
        SecureMachine secureMachine = SecureMachine.getInstance();
        CalculateMachine calculateMachine = CalculateMachine.getInstance();
        CoreFactory coreFactory = CoreFactory.getInstance();
        CheckMachine checkMachine = CheckMachine.getInstance();
        while(true){
            displayMachine.displayFunctionOption();
            int option = secureMachine.validInput(secureMachine.getInput(),2);
            initMachine.setWallet_Money(coreFactory.handleFunctionOption(option,
                    initMachine.getWallet_Money(),displayMachine,secureMachine,calculateMachine, initMachine,checkMachine));
        }

    }
}
