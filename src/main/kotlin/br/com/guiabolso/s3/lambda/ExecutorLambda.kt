package br.com.guiabolso.s3.lambda

import br.com.guiabolso.s3.reader.Reader
import br.com.guiabolso.sftp.SFTPSender
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class ExecutorLambda(val butketName: String, val key: String) {

    fun exec () {
        println("Start execução evento lambda.")
        val time = measureTimeMillis {

            val reader = Reader(butketName, key)

            val file = reader.readFile()

            val sftpSender = SFTPSender()
            sftpSender.send(file)
        }
        println("Finish execução evento lambda.")
        println("Tempo de execução: ${TimeUnit.MILLISECONDS.toSeconds(time)}")
    }
}