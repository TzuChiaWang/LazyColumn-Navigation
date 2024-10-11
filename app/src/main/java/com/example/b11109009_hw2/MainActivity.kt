package com.example.b11109009_hw2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //設定佈局畫面
        setContent {
            val attractions = listOf(
                Attraction("阿里山國家公園", "阿里山國家森林遊樂區擁有森林小火車、神木、雲海、日出與晚霞「五奇」景致，是台灣最具知名、也最受歡迎的森林遊樂區，是阿里山山脈上的一枚翡翠，閃著耀眼的光芒。", R.drawable.a_limountain, "阿里山國家森林遊樂區"),
                Attraction("日月潭", "日月潭，是一個位在臺灣南投縣魚池鄉日月村的半天然淡水湖泊兼水力發電用水庫；該潭是臺灣本島面積第二大的湖泊及第一大半天然湖泊兼發電用水庫。", R.drawable.sun_moon_lake, "日月潭"),
                Attraction("小琉球", "琉球嶼，俗稱小琉球，原名沙馬基、拉美島，荷蘭人曾稱金獅島，是台灣島西南方的一座外島，全境屬屏東縣琉球鄉管轄，為珊瑚礁石灰岩島嶼，面積6.802平方公里，位於屏東縣東港、高屏溪出海口西南方海面約15公里處，島上有許多石灰岩洞穴，全島在大鵬灣國家風景區範圍之內。", R.drawable.liuqiu_island, "琉球嶼"),
                Attraction("太魯閣國家公園", "太魯閣國家公園是中華民國第四座成立的國家公園。第二次世界大戰後為國家級風景區，園內有台灣第一條東西橫貫公路通過，稱為中橫公路系統。", R.drawable.taroko, "太魯閣國家公園"),
                Attraction("鹿港老街", "鹿港老街位於臺灣彰化縣鹿港鎮，廣義的『鹿港老街』一詞，範圍包含整個鹿港鎮的歷史古蹟街區，狹義的定義則主要由瑤林街與埔頭街連結而成，鹿港是荷蘭及清治時期中臺灣最重要對外經商港口，過往曾因商業的發展而繁榮。", R.drawable.lukan, "鹿港老街"),
                Attraction("司馬庫斯", "司馬庫斯，是位於台灣新竹縣尖石鄉後山高海拔（海拔約1500公尺）的一個泰雅族部落，居民全為泰雅族原住民。由於位處深山交通不便，且長期沒有電力供應，曾被稱為黑暗部落，也是台灣最深山的原住民部落，後來因為發現神木群所以發展觀光，現在成為一個重要的觀光地點。由於位置偏遠，距離山下最大也是最近的鄉鎮竹東鎮也要將近三個小時的車程。", R.drawable.smangus_wiki, "司馬庫斯"),
                Attraction("馬祖列島", "馬祖列島是隸屬中華民國福建省連江縣的群島，位於臺灣海峽正北方，面臨閩江口、連江口和羅源灣，距中國大陸最近點約9.25公里。主要由南竿島、北竿島、高登島、亮島、東莒島、西莒島、東引島、西引島及附屬小島共計36個島嶼、礁嶼組成，面積29.6平方公里，居民人口13,000多人。", R.drawable.masku, "馬祖列島"),
                Attraction("墾丁國家公園", "墾丁國家公園是台灣在戰後第一個成立的國家公園，成立於1982年，隸屬內政部國家公園署，下設管理處。全境位於台灣島南端的恆春半島，範圍包括屏東縣恆春鎮、滿州鄉、車城鄉部分陸域及周邊海域，以墾丁里名之。", R.drawable.kandin, "墾丁國家公園"),
                Attraction("澎湖南方四島國家公園", "澎湖南方四島國家公園是中華民國第九座國家公園，同時也是臺灣第二座海洋型國家公園。自2010年開始籌設，2014年6月8日正式公告實施，並於10月18日掛牌。", R.drawable.paunhu, "澎湖南方四島國家公園"),
                Attraction("南庄老街", "南庄老街位於苗栗縣南庄鄉永昌宮附近的中正路及一旁小巷內，緊鄰南庄遊客中心，當地最夯的伴手禮就是特產桂花釀，因此又稱桂花巷，必嚐的小吃美食包含豬籠粄、狗薑粽、桂花冰鎮湯圓、桂花梅、擂茶、滷豆干、客家菜等。老街尾端的「洗衫坑」是以前婦女洗衣服、聊天話家常的地方，老街的南庄老郵局、永昌宮、乃木崎及南庄戲院等景點都是遊客不能錯過的重點。", R.drawable.nantrun, "南庄老街"),
                )

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "attractionList") {
                composable("attractionList") {
                    AttractionListScreen(attractions) { attraction ->
                        navController.navigate("attractionDetail/${attraction.name}")
                    }
                }
                composable("attractionDetail/{name}") { backStackEntry ->
                    val attractionName = backStackEntry.arguments?.getString("name")
                    val attraction = attractions.find { it.name == attractionName }
                    if (attraction != null) {
                        AttractionDetailScreen(attraction, navController)
                    }
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        val attractions = listOf(
            Attraction("阿里山國家公園", "阿里山國家森林遊樂區擁有森林小火車、神木、雲海、日出與晚霞「五奇」景致，是台灣最具知名、也最受歡迎的森林遊樂區，是阿里山山脈上的一枚翡翠，閃著耀眼的光芒。", R.drawable.a_limountain, "阿里山國家森林遊樂區"),
            Attraction("日月潭", "日月潭，是一個位在臺灣南投縣魚池鄉日月村的半天然淡水湖泊兼水力發電用水庫；該潭是臺灣本島面積第二大的湖泊及第一大半天然湖泊兼發電用水庫。", R.drawable.sun_moon_lake, "日月潭"),
            Attraction("小琉球", "琉球嶼，俗稱小琉球，原名沙馬基、拉美島，荷蘭人曾稱金獅島，是台灣島西南方的一座外島，全境屬屏東縣琉球鄉管轄，為珊瑚礁石灰岩島嶼，面積6.802平方公里，位於屏東縣東港、高屏溪出海口西南方海面約15公里處，島上有許多石灰岩洞穴，全島在大鵬灣國家風景區範圍之內。", R.drawable.liuqiu_island, "琉球嶼"),
            Attraction("太魯閣國家公園", "太魯閣國家公園是中華民國第四座成立的國家公園。第二次世界大戰後為國家級風景區，園內有台灣第一條東西橫貫公路通過，稱為中橫公路系統。", R.drawable.taroko, "太魯閣國家公園"),
            Attraction("鹿港老街", "鹿港老街位於臺灣彰化縣鹿港鎮，廣義的『鹿港老街』一詞，範圍包含整個鹿港鎮的歷史古蹟街區，狹義的定義則主要由瑤林街與埔頭街連結而成，鹿港是荷蘭及清治時期中臺灣最重要對外經商港口，過往曾因商業的發展而繁榮。", R.drawable.lukan, "鹿港老街"),
            Attraction("司馬庫斯", "司馬庫斯，是位於台灣新竹縣尖石鄉後山高海拔（海拔約1500公尺）的一個泰雅族部落，居民全為泰雅族原住民。由於位處深山交通不便，且長期沒有電力供應，曾被稱為黑暗部落，也是台灣最深山的原住民部落，後來因為發現神木群所以發展觀光，現在成為一個重要的觀光地點。由於位置偏遠，距離山下最大也是最近的鄉鎮竹東鎮也要將近三個小時的車程。", R.drawable.smangus_wiki, "司馬庫斯"),
            Attraction("馬祖列島", "馬祖列島是隸屬中華民國福建省連江縣的群島，位於臺灣海峽正北方，面臨閩江口、連江口和羅源灣，距中國大陸最近點約9.25公里。主要由南竿島、北竿島、高登島、亮島、東莒島、西莒島、東引島、西引島及附屬小島共計36個島嶼、礁嶼組成，面積29.6平方公里，居民人口13,000多人。", R.drawable.masku, "馬祖列島"),
            Attraction("墾丁國家公園", "墾丁國家公園是台灣在戰後第一個成立的國家公園，成立於1982年，隸屬內政部國家公園署，下設管理處。全境位於台灣島南端的恆春半島，範圍包括屏東縣恆春鎮、滿州鄉、車城鄉部分陸域及周邊海域，以墾丁里名之。", R.drawable.kandin, "墾丁國家公園"),
            Attraction("澎湖南方四島國家公園", "澎湖南方四島國家公園是中華民國第九座國家公園，同時也是臺灣第二座海洋型國家公園。自2010年開始籌設，2014年6月8日正式公告實施，並於10月18日掛牌。", R.drawable.paunhu, "澎湖南方四島國家公園"),
            Attraction("南庄老街", "南庄老街位於苗栗縣南庄鄉永昌宮附近的中正路及一旁小巷內，緊鄰南庄遊客中心，當地最夯的伴手禮就是特產桂花釀，因此又稱桂花巷，必嚐的小吃美食包含豬籠粄、狗薑粽、桂花冰鎮湯圓、桂花梅、擂茶、滷豆干、客家菜等。老街尾端的「洗衫坑」是以前婦女洗衣服、聊天話家常的地方，老街的南庄老郵局、永昌宮、乃木崎及南庄戲院等景點都是遊客不能錯過的重點。", R.drawable.nantrun, "南庄老街"),

        )
        AttractionListScreen(attractions) { /* onClick handler */ }
    }

    @Composable
    fun AttractionListScreen(attractions: List<Attraction>, onClick: (Attraction) -> Unit) {
        LazyColumn {
            items(attractions) { attraction ->
                AttractionListItem(attraction, onClick)
            }
        }
    }

    @Composable
    fun AttractionListItem(attraction: Attraction, onClick: (Attraction) -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = { onClick(attraction) }),
            )
        {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = attraction.imageResourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = attraction.name,
                    )
            }
        }
    }



    //忽略警告
    @SuppressLint("QueryPermissionsNeeded")
    @Composable
    fun AttractionDetailScreen(attraction: Attraction, navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(text = "Back")
            }
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = attraction.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = attraction.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = attraction.description,
            )
            Spacer(modifier = Modifier.height(100.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    val gmmIntentUri = Uri.parse("geo:0,0?q=${attraction.location}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(text = "View on Google Maps")
            }
        }
    }

    }
    private fun showGoogleMapsUnavailableDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Google Maps 不可用")
            .setMessage("您的設備上未安裝Google Maps應用，無法顯示地圖。請安裝Google Maps或使用其他地圖應用。")
            .setPositiveButton("確定") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
