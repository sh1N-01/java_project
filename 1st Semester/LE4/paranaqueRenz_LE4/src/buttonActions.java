/*
 * Class: buttonActions
 * -> This class handles the logic for button interactions and updates the GUI accordingly.
 * 
 * Exclusive Functions:
 * actionListener() - Method for setting up button actions and event listeners.
 * @param - Button, Button, Button, Button, Button, Button, Button, Button, Button, TextArea, Button, Button
 * @return - void
 * 
 * dimButton() - Method for applying visual effects to buttons based on selection.
 * @param - int, Button, Button, Button, Button, ColorAdjust
 * @return - void
 * 
 * reset() - Method for resetting button states and counters.
 * @param - Button, Button, Button
 * @return - void
 * 
 * tooltip() - Method for setting tooltips for buttons based on dispenser details.
 * @param - dispenserType, dispenserType, dispenserType, dispenserType, Button, Button, Button, Button
 * @return - void
 * 
 * juiceEnabler() - Method for enabling or disabling juice buttons.
 * @param - Boolean, Button, Button, Button, Button
 * @return - void
 * 
 * list() - Method for displaying the list of orders in the TextArea.
 * @param - TextArea, int[], String[]
 * @return - void
 * 
 * updateList() - Method for updating the list of orders and button states.
 * @param - int, int, TextArea, Button, Button, Button, Button, Button, Button, Button
 * @return - void
 * 
 * checkout() - Method for displaying checkout details in the TextArea.
 * @param - TextArea, int, int, int, int, int, int, int, int, int
 * @return - void
 * 
 * update() - Method for handling post-payment updates to the GUI and dispenser stocks.
 * @param - int, TextArea, Button, Button, Button, Button, Button, Button, Button, Button, Button, dispenserType, dispenserType, dispenserType, dispenserType, Button
 * @return - void
 */
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class buttonActions 
{
    private int count = 0;
    private int total = 0;
    private int flag;
    private boolean enableOthers = false;
    private String[] items = {"Orange Juice", "Apple Juice", "Mango Lassi", "Fruit Punch"};
    private int[] userOrder = new int[4];
    private int sumStuff = 0;

    public buttonActions()
    {
        
    }
    
    public void actionListener(Button orange, Button apple, Button mango, Button fruit, 
    Button add, Button sub, Button registerBalance, 
    Button dispense, Button itemCounter, TextArea display, Button addToOrder, Button removeOrder)
    {
        cashRegister register = new cashRegister();
        
        dispenserType orangE = new dispenserType();
        dispenserType mangO = new dispenserType();
        dispenserType applE = new dispenserType();
        dispenserType fruiT = new dispenserType();
        
        ColorAdjust dimButton = new ColorAdjust();
        dimButton.setBrightness(-0.5);

        int[] stocks = {orangE.getNumberOfItems(), applE.getNumberOfItems(), mangO.getNumberOfItems(), fruiT.getNumberOfItems()};

        tooltip(orangE, applE, mangO, fruiT, orange, apple, mango, fruit);

        orange.setOnAction(e-> 
        {
            dimButton(1, orange, apple, mango, fruit, dimButton);
            this.flag = 0;
            reset(itemCounter, sub, addToOrder);
            add.setDisable(false);
        });

        apple.setOnAction(e-> 
        {
            dimButton(2, orange, apple, mango, fruit, dimButton);
            this.flag = 1;
            reset(itemCounter, sub, addToOrder);
            add.setDisable(false);
        });

        mango.setOnAction(e-> 
        {
            dimButton(3, orange, apple, mango, fruit, dimButton);
            this.flag = 2;
            reset(itemCounter, sub, addToOrder);
            add.setDisable(false);
        });

        fruit.setOnAction(e-> 
        {
            dimButton(4, orange, apple, mango, fruit, dimButton);
            this.flag = 3;
            reset(itemCounter, sub, addToOrder);
            add.setDisable(false);
        });

        add.setOnAction(e -> 
        {
            addToOrder.setDisable(false);

            if(this.count != stocks[this.flag])
            {
                ++this.count;
                itemCounter.setText(Integer.toString(this.count));
            }

            if(this.count == stocks[this.flag])
            {
                add.setDisable(true);
            }

            sub.setDisable(false);
        });

        sub.setOnAction(e -> 
        {
            if(this.count > 0)
            {
                --this.count;
                itemCounter.setText(Integer.toString(this.count));
            }

            if(this.count == 0)
            {
                addToOrder.setDisable(true);
                sub.setDisable(true);
            }

            add.setDisable(false);
        });

        registerBalance.setOnAction(e -> 
        {
            register.currentBalance(display);
        });

        addToOrder.setOnAction(e -> 
        {
            if(stocks[this.flag] == 0)
            {
                display.appendText("\n" + items[this.flag] + " is out of stock.");
                reset(itemCounter, sub, addToOrder);
                return;
            }

            else
            {
                userOrder[this.flag] += this.count;
                stocks[this.flag] -= this.count;
    
                display.appendText("\nAdded " + this.count + " " + items[this.flag] + "(s) to order.");
                enableOthers = true;
                if(enableOthers)
                {
                    removeOrder.setDisable(false);
                    dispense.setDisable(false);
                }
            }
        });

        removeOrder.setOnAction(e->
        {
            addToOrder.setDisable(true);
            add.setDisable(true);
            sub.setDisable(true);
            dispense.setDisable(true);
            registerBalance.setDisable(true);
            juiceEnabler(true, orange, apple, mango, fruit);

            for(int i = 0; i < 4; i++)
            {
                this.total += userOrder[i];
            }

            display.setText("== LIST OF ORDER(s) ==");
            list(display, userOrder, items);

            display.appendText("\n\nPress the corresponding number key to select the juice to remove");
            for(int i = 0; i < 4; i++)
            {
                if(userOrder[i] != 0)
                {
                    display.appendText("\n" + (i+1) + ". " + items[i]);
                }
            }

            removeOrder.setOnKeyPressed(ie->
            {
                KeyCode key = ie.getCode();
                if(key == KeyCode.DIGIT1 && userOrder[0] != 0)
                {
                    int n = orangE.popDialog(items[0], userOrder[0]);
                    stocks[0] += n;
                    updateList(0, n, display, removeOrder, orange, apple, mango, fruit, registerBalance, dispense, itemCounter);
                }
                
                if(key == KeyCode.DIGIT2 && userOrder[1] != 0)
                {
                    int n = applE.popDialog(items[1], userOrder[1]);
                    stocks[1] += n;
                    updateList(1, n, display, removeOrder, orange, apple, mango, fruit, registerBalance, dispense, itemCounter);
                }
                
                if(key == KeyCode.DIGIT3 && userOrder[2] != 0)
                {
                   int n = mangO.popDialog(items[2], userOrder[2]);
                   stocks[2] += n;
                   updateList(2, n, display, removeOrder, orange, apple, mango, fruit, registerBalance, dispense, itemCounter);
                }
                
                if(key == KeyCode.DIGIT4 && userOrder[3] != 0)
                {
                    int n = fruiT.popDialog(items[3], userOrder[3]);
                    stocks[3] += n;
                    updateList(3, n, display, removeOrder, orange, apple, mango, fruit, registerBalance, dispense, itemCounter);
                }
            });
        });

        dispense.setOnAction(e-> 
        {
            int oCost = orangE.getCost() * userOrder[0];
            int aCost = applE.getCost() * userOrder[1];
            int mCost = mangO.getCost() * userOrder[2];
            int fCost = fruiT.getCost() * userOrder[3];

            int totalCost = oCost + aCost + mCost + fCost;

            checkout(display, oCost, aCost, mCost, fCost, totalCost, orangE.getCost(), applE.getCost(), mangO.getCost(), fruiT.getCost());
            
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(ev -> 
            {
                int mn = register.acceptAmount(totalCost);
                update(mn, display, removeOrder, orange, apple, mango, fruit, registerBalance, dispense, itemCounter, addToOrder, sub, orangE, applE, mangO, fruiT, add);

            });
            pause.play();
        });

        itemCounter.setOnAction(e->
        {
            this.sumStuff++;
            if(this.sumStuff == 10)
            {
                display.clear();
                this.sumStuff = 0;
            }
        });
    }

    private void dimButton(int i, Button orange, Button apple, Button mango, Button fruit, ColorAdjust dim)
    {
        switch(i)
        {
            case 1:
                orange.setEffect(dim);
                apple.setEffect(null);
                mango.setEffect(null);
                fruit.setEffect(null);
                break;
            case 2:
                orange.setEffect(null);
                apple.setEffect(dim);
                mango.setEffect(null);
                fruit.setEffect(null);
                break;
            case 3:
                orange.setEffect(null);
                apple.setEffect(null);
                mango.setEffect(dim);
                fruit.setEffect(null);
                break;
            case 4:
                orange.setEffect(null);
                apple.setEffect(null);
                mango.setEffect(null);
                fruit.setEffect(dim);
                break;
            default:
                orange.setEffect(null);
                apple.setEffect(null);
                mango.setEffect(null);
                fruit.setEffect(null);
                break;
        }
    }

    private void reset(Button itemCounter, Button sub, Button addToOrder)
    {
        this.count = 0;
        itemCounter.setText(Integer.toString(this.count));
        sub.setDisable(true);
        addToOrder.setDisable(true);
    }

    private void tooltip(dispenserType orangE, dispenserType applE, dispenserType mangO, dispenserType fruiT, Button orange, Button apple, Button mango, Button fruit)
    {
        Tooltip orangeDE = new Tooltip("Orange Juice\nCost: " + orangE.getCost() + " cents\nStock: " + orangE.getNumberOfItems());
        Tooltip appleDE = new Tooltip("Apple Juice\nCost: " + applE.getCost() + " cents\nStock: " + applE.getNumberOfItems());
        Tooltip mangoDE = new Tooltip("Mango Lassi\nCost: " + mangO.getCost() + " cents\nStock: " + mangO.getNumberOfItems());
        Tooltip fruitDE = new Tooltip("Fruit Punch\nCost: " + fruiT.getCost() + " cents\nStock: " + fruiT.getNumberOfItems());
        
        Tooltip.install(orange, orangeDE);
        Tooltip.install(apple, appleDE);
        Tooltip.install(mango, mangoDE);
        Tooltip.install(fruit, fruitDE);
    }

    private void juiceEnabler(Boolean n, Button orange, Button apple, Button mango, Button fruit)
    {
        if(n == true)
        {
            orange.setDisable(n);
            apple.setDisable(n);
            mango.setDisable(n);
            fruit.setDisable(n);
        }

        else
        {
            orange.setDisable(n);
            apple.setDisable(n);
            mango.setDisable(n);
            fruit.setDisable(n);
        }
    }

    private void list(TextArea display, int[] userOrder, String[] items)
    {
        for(int i = 0; i < 4; i++)
        {
            if(userOrder[i] != 0)
            {
                display.appendText("\n" + items[i] + ": " + userOrder[i]);
            }
        }
    }

    private void updateList(int i, int n, TextArea display, Button removeOrder, Button orange, Button apple, Button mango, Button fruit, Button registerBalance, Button dispense, Button itemCounter)
    {   
        this.total = 0;

        userOrder[i] -= n;

        for(int j = 0; j < 4; j++)
        {
            this.total += userOrder[j];
        }

        if(this.total != 0)
        {
            display.setText("==UPDATED LIST OF ORDER(s)==");
            list(display, userOrder, items);

            juiceEnabler(false, orange, apple, mango, fruit);
            dimButton(-1, orange, apple, mango, fruit, null);
            registerBalance.setDisable(false);
            dispense.setDisable(false);

            this.count = 0;
            itemCounter.setText(Integer.toString(this.count));
        }

        if(this.total == 0)
        {
            display.setText("All orders have been removed.");
            removeOrder.setDisable(true);
            registerBalance.setDisable(false); 
            juiceEnabler(false, orange, apple, mango, fruit);
            dimButton(-1, orange, apple, mango, fruit, null);

            this.count = 0;
            itemCounter.setText(Integer.toString(this.count));
        }
    }

    private void checkout(TextArea display, int o, int a, int m, int f, int total, int or, int ap, int ma, int fr)
    {
        display.setText("==CHECKOUT==");
        if(o != 0)
        {
            display.appendText("\n" + items[0] + " Purchased: " + userOrder[0]);
            display.appendText("\nPrice: " + or + " cents");
            display.appendText("\nTotal Price: " + o + " cents\n");
        }

        if(a != 0)
        {
            display.appendText("\n" + items[1] + " Purchased: " + userOrder[1]);
            display.appendText("\nPrice: " + ap + " cents");
            display.appendText("\nTotal Price: " + a + " cents\n");
        }

        if(m != 0)
        {
            display.appendText("\n" + items[2] + " Purchased: " + userOrder[2]);
            display.appendText("\nPrice: " + ma + " cents");
            display.appendText("\nTotal Price: " + m + " cents\n");
        }

        if(f != 0)
        {
            display.appendText("\n" + items[3] + " Purchased: " + userOrder[3]);
            display.appendText("\nPrice: " + fr + " cents");
            display.appendText("\nTotal Price: " + f + " cents\n");
        }
        display.appendText("\nTotal Cost: " + total + " cents");
    }

    private void update(int mn, TextArea display, Button removeOrder, Button orange, Button apple, Button mango, Button fruit, Button registerBalance, Button dispense, Button itemCounter, Button addToOrder, Button sub, dispenserType orangE, dispenserType applE, dispenserType mangO, dispenserType fruiT, Button add)
    {
        if(mn == -1)
        {
            display.setText("Transaction Cancelled.");
            reset(itemCounter, sub, addToOrder);
            dimButton(mn, orange, apple, mango, fruit, null);
            juiceEnabler(false, orange, apple, mango, fruit);
            removeOrder.setDisable(true);
            addToOrder.setDisable(true);
            dispense.setDisable(true);

            this.total = 0;
            for(int i = 0; i < 4; i++)
            {
                userOrder[i] = 0;
            }
            
        }

        if(mn == 1)
        {
            orangE.updateNumberOfItems(userOrder[0]);
            applE.updateNumberOfItems(userOrder[1]);
            mangO.updateNumberOfItems(userOrder[2]);
            fruiT.updateNumberOfItems(userOrder[3]);

            for(int i = 0; i < 4; i++)
            {
                userOrder[i] = 0;
            }

            tooltip(orangE, applE, mangO, fruiT, orange, apple, mango, fruit);
            reset(itemCounter, sub, addToOrder);
            dimButton(mn, orange, apple, mango, fruit, null);
            juiceEnabler(false, orange, apple, mango, fruit);
            removeOrder.setDisable(true);
            addToOrder.setDisable(true);
            dispense.setDisable(true);
            add.setDisable(true);

            this.total = 0;

            display.appendText("\n\nTransaction Successful. Enjoy your drink(s)!");
        }
    }
}
