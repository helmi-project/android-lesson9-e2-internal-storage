package com.androidatc.lesson09_e2_internalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    // var file will be consider the internal storage file
    var file = "hello_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        When the app user clicks Save button, he/she will Create an
        internal storage file called "file" and save the date which will
        be written inside the enterText Plain Text widget
         */
        saveBtn.setOnClickListener {
            try {
                var fOut: FileOutputStream = openFileOutput(file,Context. MODE_PRIVATE)

                /*
                On Android strings are converted to UTF-8 byte sequences when
                Sending filenames to the operating system, and byte sequences
                returned by the operating system are converted to strings by
                decoding them as UTF-8 byte sequences
                 */
                fOut.write(enterText.text.toString().toByteArray(Charsets.UTF_8))
                fOut.close()

                Toast.makeText(this,"File Saved",Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /*
        When the user clicks Load button, the internal storage file content will appear
        inside the TextView widget which has resultArea Id
         */
        loadBtn.setOnClickListener {
            try {
                val fIn = openFileInput(file)
                var temp = ""
                var bytes: ByteArray = fIn.readBytes()

                for (byte in bytes) {
                    temp += byte.toChar()
                }
                resultArea.setText(temp)

                Toast.makeText(this,"File Read",Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
