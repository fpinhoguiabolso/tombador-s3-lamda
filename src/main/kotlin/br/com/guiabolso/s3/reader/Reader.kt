package br.com.guiabolso.s3.reader

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import java.io.InputStream

class Reader(val bucketName:String, val key:String) {
    fun readFile(): Content {
        println("Start read s3")
        val s3Client = AmazonS3ClientBuilder.defaultClient()
        println("Get object $key bucketname $bucketName")
        val objectS3 = s3Client.getObject(bucketName, key)

        println("Read content object")
        val content = objectS3.objectContent
        println("Finish read s3")
        //objectS3.close()
        return Content(key, content)
    }
}

data class Content(val nome:String, val content: InputStream)