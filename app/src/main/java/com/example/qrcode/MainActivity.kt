package com.example.qrcode

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var Bgenerate:Button? = null
    var edT: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        im = findViewById(R.id.imageView)
        Bgenerate = findViewById(R.id.button)
        edT = findViewById(R.id.editTextText)

        Bgenerate?.setOnClickListener {
            generateQrCode("lol")
        }
    }
    private fun generateQrCode(text: String){
        try {
        val barcodeEncode: BarcodeEncoder= BarcodeEncoder()
        val bitmap: Bitmap = barcodeEncode.encodeBitmap(text,BarcodeFormat.QR_CODE, 700,700)
        //val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 200)
            //val bMap = qrGenerator.encodeAsBitmap()
            im?.setImageBitmap(bitmap)
        }catch (e: WriterException){

        }
    }
}