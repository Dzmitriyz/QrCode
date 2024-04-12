package com.example.qrcode

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var Bgenerate: Button? = null
    var bShare: Button? = null
    var edT: EditText? = null
    var bitmapMain: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        Bgenerate?.setOnClickListener {
            if (edT != null) {
                generateQrCode(edT?.text.toString())
                bShare?.visibility = View.VISIBLE
            }else{
                toastMessage()
            }
        }
        bShare?.setOnClickListener {
            val bitmpath = MediaStore.Images.Media.insertImage(contentResolver, bitmapMain, "upload", null)
            val uri = Uri.parse(bitmpath)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "img/png"
                putExtra(Intent.EXTRA_STREAM, uri)
            }
            val shareIntent = Intent.createChooser(sendIntent,getString(R.string.share_qr))
            startActivity(shareIntent)
        }
    }


    private fun init(){
        im = findViewById(R.id.imageView)
        Bgenerate = findViewById(R.id.button)
        edT = findViewById(R.id.editTextText)
        bShare = findViewById(R.id.share)
    }

    private fun generateQrCode(text: String) {
        try {
            val barcodeEncode: BarcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncode.encodeBitmap(text, BarcodeFormat.QR_CODE, 700, 700)
            im?.setImageBitmap(bitmap)
            bitmapMain =bitmap
        } catch (e: WriterException) {

        }
    }
    private fun toastMessage() {
        var toast = Toast.makeText(this, "Введите текст", Toast.LENGTH_LONG)
        toast.show()
    }


}