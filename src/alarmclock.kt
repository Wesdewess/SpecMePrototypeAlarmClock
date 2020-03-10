import java.awt.Color.gray
import java.awt.Color.red
import java.awt.EventQueue
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JButton
import javax.swing.JFrame

//Creative media and game technologies, Specialise me 2020
//assignment: create an alarm clock that invokes a certain reaction in a user
//1 hour to come up with an idea, create a prototype and test it with a user

//our idea: an alarm clock that either makes the user listen to it for 10 minutes when it goes off,
//or the user can press the button to snooze it immediately, but a kitten gets hurt when doing so.
fun main() {
    EventQueue.invokeLater(::createAndShowGUI) //run the method that creates the GUI
}

//the alarm clock object of type JFrame
class SwingAlarmClock(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        setTitle(title)
        setSize(1000,600)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
    }
}

private fun createAndShowGUI() {
    //create a button to press, then specify its attributes
    val button = JButton("Je wekker gaat af en gaat 10 minuten lang door \n SNOOZE de wekker om hem te stoppen, \n maar dan word er een kitten uit het raam gegooid")
    button.alignmentX = 50F
    button.alignmentY = 50F
    button.setSize(10,20)
    button.background = red
    button.isVisible = true

    //create the frame in which to place the button
    val frame = SwingAlarmClock("Kitten wekker")
    frame.add(button)
    frame.isVisible = true

    //use an executor to run this function on a timer
    //turn the button red and then grey on a timer
    val helloRunnable = Runnable {
        if (button.background == gray){
            button.background=red
        }else{
            button.background= gray
        }
    }

    //executor to run the above function on a timer
    val executor = Executors.newScheduledThreadPool(1)
    executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS)

    //specify what happens when you press the button
    //the alarm clock gets snoozed and tells the user that the kitten got hurt
    button.addActionListener {
        button.text = "Wekker is gesnoozed maar de kitten heeft pijn!"
        button.background = gray
        executor.shutdownNow() //stop running the function on a timer
    }
}

