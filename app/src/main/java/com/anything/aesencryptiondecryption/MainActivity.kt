package com.anything.aesencryptiondecryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {
    var key : String="mysecretkey12345" //You can use any key
    var secretKeySpec = SecretKeySpec(key.toByteArray(),"AES")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et1=findViewById<EditText>(R.id.et1)
        var btnencrypt = findViewById<Button>(R.id.encryptBtn)
        var tvencrypt=findViewById<TextView>(R.id.encryptTV)
        var et2=findViewById<EditText>(R.id.et2)
        var btndecrypt = findViewById<Button>(R.id.decryptBtn)
        var tvDecrypt=findViewById<TextView>(R.id.decryptTV)

        btnencrypt.setOnClickListener{
            tvencrypt.setText(encrypt(et1.text.toString()))
            et2.setText(encrypt(et1.text.toString()))
        }


        btndecrypt.setOnClickListener{
            tvDecrypt.setText(decrypt(et2.text.toString()))
        }

        //It may lead to crash if u will give empty text in edit text


    }

    private fun encrypt(string: String) : String{
        var cipher = Cipher.getInstance("AES/ECB/PKCS5Padding") //Specifying which mode of AES is to be used
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec)// Specifying the mode wither encrypt or decrypt
        var encryptBytes =cipher.doFinal(string.toByteArray(Charsets.UTF_8))//Converting the string that will be encrypted to byte array
        return Base64.encodeToString(encryptBytes,Base64.DEFAULT) // returning the encrypted string
    }

    private fun decrypt(string : String) : String{
        var cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec)
        var decryptedBytes = cipher.doFinal(Base64.decode(string,Base64.DEFAULT)) // decoding the entered string
        return String(decryptedBytes,Charsets.UTF_8) // returning the decrypted string
    }



}