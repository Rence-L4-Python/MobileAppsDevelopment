package com.example.plottery

import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val goal= arrayOf("Random","Retrieve","Defeat","Escape","Expose","Master","Persuade","Adapt")
    val conflict= arrayOf("Random","Character","Society","Nature","Technology","Fate","Supernatural","Self")
    val stakes= arrayOf("Random","Personal","Relational","Widespread","Moral","Reputational","Aspirational")
// dictionaries of pairs for the different arrays
    val goalDict = mapOf(
        "Retrieve" to listOf(
            "recover what was lost",
            "claim a hidden treasure",
            "secure a vital object",
            "bring back a missing piece"
        ),
        "Defeat" to listOf(
            "overcome a powerful foe",
            "crush a rising threat",
            "dismantle the enemy's plan",
            "end a reign of terror"
        ),
        "Escape" to listOf(
            "break free from captivity",
            "flee from looming danger",
            "slip past from watchful eyes",
            "vanish before it's too late"
        ),
        "Expose" to listOf(
            "reveal a buried truth",
            "unmask the hidden traitor",
            "bring secrets into light",
            "uncover a dangerous lie"
        ),
        "Master" to listOf(
            "train and master a rare skill",
            "seek control over a newfound power",
            "work to understand what they've become",
            "unlock secrets hidden deep within themselves"
        ),
        "Persuade" to listOf(
            "win over a wary heart",
            "change a stubborn mind",
            "sway a group's decision",
            "inspire others to act"
        ),
        "Adapt" to listOf(
            "quickly learn something new",
            "survive in unfamiliar lands",
            "adjust to a shifting world",
            "make a change while staying true to themselves"
        ),
    )
    val conflictDict = mapOf(
        "Character" to listOf(
            "clash with a rival's ambition",
            "face betrayal from a trusted ally",
            "struggle to outmatch another's will",
            "compete for power or pride"
        ),
        "Society" to listOf(
            "challenge unjust traditions",
            "resist the weight of expectations",
            "walk alone in their path",
            "defy what their world demands"
        ),
        "Nature" to listOf(
            "endure nature's wrath",
            "fight to survive in a hostile land",
            "step into the unknown",
            "confront a force out of their control"
        ),
        "Technology" to listOf(
            "wrestle with tools they don't understand",
            "rely on inventions they can't trust",
            "face technical failures",
            "face progress with a human cost"
        ),
        "Fate" to listOf(
            "are pulled by forces beyond their control",
            "question what was written in the stars",
            "escape their destiny",
            "fight the future they fear"
        ),
        "Supernatural" to listOf(
            "face what reason can't explain",
            "are haunted by things unseen",
            "carry a curse no one else believes",
            "bargain with powers beyond reach"
        ),
        "Self" to listOf(
            "confront the darkness within",
            "battle their own fears and flaws",
            "question who they truly are",
            "fight to control their impulses"
        )
    )
    val stakesDict = mapOf(
        "Personal" to listOf(
            "their own safety hangs in the balance",
            "they risk losing what matters most to them",
            "what it takes could crush their soul",
            "their future depends on this choice"
        ),
        "Relational" to listOf(
            "their closest bonds may be shattered",
            "trust between friends could be lost forever",
            "a fragile relationship stands to crumble",
            "they might alienate those they love"
        ),
        "Widespread" to listOf(
            "the fate of many rests on their success",
            "an entire world could fall into chaos",
            "lives could be lost at any moment",
            "a wider world may suffer the consequences"
        ),
        "Moral" to listOf(
            "doing what's right might cost everything",
            "their conscience will not allow them to betray it",
            "the line between right and wrong blurs dangerously",
            "they face a choice that tests their integrity"
        ),
        "Reputational" to listOf(
            "their name could be forever tarnished",
            "their honor stands at the brink of ruin",
            "something small could destroy what they've done",
            "all they've built could be undone"
        ),
        "Aspirational" to listOf(
            "their dreams may slip just out of reach",
            "their chance to rise above could vanish",
            "they risk failing their highest ambitions",
            "their journey towards greatness could end here"
        )
    )

    fun showpopupinfo(a: Int){ // since we will be using different popups, this will be used instead of a constant R.layout id
        val inflater = layoutInflater
        val dialogView = inflater.inflate(a, null) // a is used as a placeholder
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<ImageButton>(R.id.popup_close).setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()

        val popupWidth = resources.getDimensionPixelSize(R.dimen.popup_width)
        val popupHeight = resources.getDimensionPixelSize(R.dimen.popup_height)

        dialog.window?.setLayout(popupWidth, popupHeight)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val ddl1 = findViewById<Spinner>(R.id.spinner1)
        val ddl2 = findViewById<Spinner>(R.id.spinner2)
        val ddl3 = findViewById<Spinner>(R.id.spinner3)
        val preview = findViewById<TextView>(R.id.selecteditems)

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, goal)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, conflict)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, stakes)

        ddl1.adapter = adapter1
        ddl2.adapter = adapter2
        ddl3.adapter = adapter3

        val output = findViewById<TextView>(R.id.textView4)

        val goalinfo = findViewById<ImageButton>(R.id.goalinfo)
        goalinfo.setOnClickListener{
            showpopupinfo(R.layout.popup_goal) // argument for "a"
        }

        val conflictinfo = findViewById<ImageButton>(R.id.conflictinfo)
        conflictinfo.setOnClickListener{
            showpopupinfo(R.layout.popup_conflict)
        }

        val stakesinfo = findViewById<ImageButton>(R.id.stakesinfo)
        stakesinfo.setOnClickListener{
            showpopupinfo(R.layout.popup_stakes)
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val selectedGoal = if (ddl1.selectedItem == "Random"){
                goal.drop(1).random() // skips "random" in array
            } else {
                ddl1.selectedItem.toString() // if not "random", use option instead
            }
            val selectedConflict = if (ddl2.selectedItem == "Random"){
                conflict.drop(1).random()
            } else {
                ddl2.selectedItem.toString()
            }
            val selectedStakes = if (ddl3.selectedItem == "Random"){
                stakes.drop(1).random()
            } else {
                ddl3.selectedItem.toString()
            }

            val text = "($selectedGoal, $selectedConflict, $selectedStakes)".lowercase()
            preview.text = text // lets people know what they're working with if they choose "random"

            val goalMod = goalDict[selectedGoal]?.random() // select a random phrase from the dictionary with the selected option
            val conflictMod = conflictDict[selectedConflict]?.random()
            val stakesMod = stakesDict[selectedStakes]?.random()

            output.text = "The character tries to $goalMod while they $conflictMod... However $stakesMod."
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}