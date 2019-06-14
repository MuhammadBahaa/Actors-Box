package muhammadbahaa.actorbox.ui.save_image

import android.Manifest
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import muhammadbahaa.actorbox.databinding.ActivitySaveImageBinding
import muhammadbahaa.actorbox.utils.Constants
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


/**
 * Created by muhammadbahaa on 2019-06-14.
 */

class SaveImageActivity : AppCompatActivity() {

    lateinit var binding: ActivitySaveImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, muhammadbahaa.actorbox.R.layout.activity_save_image)

        val intent = intent
        val imagePath = intent.getStringExtra("image_path")

        Glide.with(this)
            .load(Constants.Image_BASE_URL + imagePath)
            .into(binding.imageView)

        binding?.button?.setOnClickListener {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    val draw = binding.imageView.getDrawable() as BitmapDrawable
                    val bitmap: Bitmap = draw.bitmap

                    saveImageFile(bitmap)
                } else {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    fun saveImageFile(bitmap: Bitmap): String {
        var out: FileOutputStream? = null
        val filename = getFilename()
        try {
            out = FileOutputStream(filename)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return filename
    }


    private fun getFilename(): String {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString(), "Actor Box"
        )
        if (!file.exists()) {
            file.mkdirs()
        }
        return (file.path + "/" + System.currentTimeMillis() + ".jpg")
    }
}
