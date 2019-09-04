package br.com.guiabolso.sftp

import br.com.guiabolso.s3.reader.Content
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session

class SFTPSender {

    val sessionSFTP: Session

    constructor() {

        val userName = System.getenv("USER_SFTP_SALES_MKT")
        val hostSftp = System.getenv("HOST_SFTP_SALES_MKT")
        val portSftp = System.getenv("PORT_SFTP_SALES_MKT").toInt()
        val password = System.getenv("PASSWORD_SFTP_SALES_MKT")

        val jsch: JSch = JSch()

        sessionSFTP = jsch.getSession(userName
            ,hostSftp
            ,portSftp)
        sessionSFTP.setPassword(password)
        sessionSFTP.setConfig("StrictHostKeyChecking", "no")
    }

    val _dirImport = "/Import"

    fun send (file: Content): Unit {

        println("Start sender sftp")
        println("Estabelecendo conexão com o sftp Sales Mkt")
        sessionSFTP.connect()

        val channelSalesMkt = sessionSFTP.openChannel("sftp")

        channelSalesMkt.connect()

        val channel = channelSalesMkt as ChannelSftp
        println("Enviando arquivo para o diretório /Import")

        channel.put(file.content , "$_dirImport/${file.nome}")

        println("Finish put sftp arquivo")
        channel.exit()
        sessionSFTP.disconnect()
        println("Finish sender sftp")
    }
}