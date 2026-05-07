package com.example.listadetarefas

import android.os.Bundle
import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        verificarSaidasAudio()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Adicionei o systemBars.top aqui para o conteúdo não ficar embaixo da barra de status
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    } private fun verificarSaidasAudio() {
        // Obtém o serviço de áudio do sistema
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Enumera as saídas de áudio disponíveis, conforme pedido no roteiro
        val devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)

        for (device in devices) {
            // Verifica se existe alto-falante (útil para alertas de emergência)
            if (device.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
                Log.d("DomaApp", "Saída encontrada: Alto-falante integrado")
            }
            // Verifica se há Bluetooth (útil para instruções de áudio privadas)
            if (device.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP) {
                Log.d("DomaApp", "Saída encontrada: Dispositivo Bluetooth")
            }
        }
    }
}
