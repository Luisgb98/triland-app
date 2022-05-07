package com.luisgb.triland

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.google.zxing.BarcodeFormat


class QRActivity : AppCompatActivity() {
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val scanResult: String = getString(R.string.scanResult)
        codeScanner = CodeScanner(this, scannerView)

        codeScanner.camera = CodeScanner.CAMERA_BACK // Especificamos la cámara trasera
        codeScanner.formats = mutableListOf(BarcodeFormat.QR_CODE) // Lista de tipos de códigos
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // Autofocus puesto
        codeScanner.scanMode = ScanMode.SINGLE // Escanaa una vez y da el resultado
        codeScanner.isAutoFocusEnabled = true // El autofocus esta activado al iniciar el lector
        codeScanner.isFlashEnabled = false // El flash esta desactivado al iniciar el lector

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, scanResult + "${it.text}", Toast.LENGTH_LONG).show()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    // Comprobamos los permisos
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@QRActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Pedimos al usuario que nos de los permisos correspondientes
            ActivityCompat.requestPermissions(this@QRActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@QRActivity, (R.string.permissionAlready), Toast.LENGTH_SHORT).show()
        }
    }

    // Esta función se activa cuando el usuario permite o deniega los permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@QRActivity, (R.string.cameraPGranted), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@QRActivity, (R.string.cameraPDenied), Toast.LENGTH_SHORT).show()
            }
        }
    }

}