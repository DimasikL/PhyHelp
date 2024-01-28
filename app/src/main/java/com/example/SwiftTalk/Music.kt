package com.example.SwiftTalk

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbotik.R
import com.example.chatbotik.databinding.ActivityMusicBinding

class Music : AppCompatActivity() {
    lateinit var binding: ActivityMusicBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.textView4.setText("Полет птицы\n" +
                "Всем нам часто снятся прекрасные сны. В них мы превращаемся в принцев и принцесс, зайчиков и белочек, понимаем язык птиц и зверей. Давайте сегодня попробуем превратиться в птицу. Мы узнаем секрет красоты и легкости, полетаем над землей, почувствуем свежесть ясного голубого неба. Оно очень похоже на море, тело в нем такое же легкое, гибкое, как в воде. Небо - это голубое царство великолепных птиц.\n" +
                "\n" +
                "Одна из самых красивых - лебедь: белая, как снег, с длинной гибкой шеей, золотистым клювом. Поднимите подбородок вверх. Да, именно такая шея у лебедя! Выпрямите спинку, расправьте плечики. Вы прекрасны, такая шея у лебедя! Закройте глаза.\n" +
                "\n" +
                "Давайте подышим, как птицы. Вдох - выдох. После вдоха говорите про себя: «Я - лебедь» - потом выдохните. Вдох: - «Я - лебедь» - выдох (3 раза}. Хорошо!\n" +
                "\n" +
                "Шея лебедя вытянута, напряжена, спина гордая, прямая. Широко раскиньте руки-крылья, шире, плавно... Молодцы!\n" +
                "\n" +
                "Согните чуть-чуть в коленях ноги, напрягите их и... резко выпрямите... Глубокий вдох... и... о, чудо! Мы взлетели... Вдох — выдох (3 раза). Вдох - выдох... Можно свободно взмахнуть крыльями. Тело, как пушинка, легкое, мы совсем его не чувствуем. Как радостно и свободно! Чистое голубое небо вокруг, приятная мягкая прохлада... тишина... покой... Вдох - выдох... (3 раза).\n" +
                "\n" +
                "Под нами, как зеркало, блестит озеро. Можно присмотреться и увидеть в нем себя. Какая красивая, гордая птица!... Вдох - выдох. Мы плавно летим, нам нравится и совсем не страшно.\n" +
                "\n" +
                "Спустимся пониже, еще ниже, еще ниже.... Вот уже совсем близко поле, лес, дом, люди.... И вот ноги легко коснулись земли.... Вытянем шейку, расправим плечи, опустим крылья.\n" +
                "\n" +
                "Замечательным был полет!\n" +
                "\n" +
                "Пусть у кого-то не очень получилось. Не беда! И лебедь-красавец не сразу взлетел. Когда-то он был Гадким утенком, перенес много горя и бедствий, над ним смеялись, его обижали, и никто не понимал. Но не страшно родиться Гадким утенком, важно хотеть быть Лебедем! Все прекрасное когда-нибудь сбывается. Нужно только очень-очень верить в себя!")
        var count = 0
        var listoffilms = listOf(R.raw.video1, R.raw.video2, R.raw.video3)
        val videoView = binding.videoView
        binding.button4.setOnClickListener {
            count --
            if (count == -1) count = 2
            val numvideo = listoffilms[count]
            val videoUrl = "android.resource://$packageName/$numvideo"
            val videoUri = Uri.parse(videoUrl);
            videoView.setVideoURI(videoUri);
        }
        binding.button5.setOnClickListener {
            count ++
            if (count == 3) count = 0
            val numvideo = listoffilms[count]
            val videoUrl = "android.resource://$packageName/$numvideo"
            val videoUri = Uri.parse(videoUrl);
            videoView.setVideoURI(videoUri);
        }
        val numvideo = listoffilms[count]
        val videoUrl = "android.resource://$packageName/$numvideo"
        val videoUri = Uri.parse(videoUrl)
        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()
        binding.button6.setOnClickListener {
            finish()
        }
    }
}
