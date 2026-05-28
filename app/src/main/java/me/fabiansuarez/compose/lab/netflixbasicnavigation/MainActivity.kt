package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.HomeScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.NetflixBasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navControl = rememberNavController()

            NavHost(
                navController = navControl,
                startDestination="login",
                modifier = Modifier.fillMaxSize()
            ){
                composable(route=Routes.LOGIN){
                    LoginScreen(onClickSuscribe = {navControl.navigate(Routes.REGISTER)})
                }
                composable(route=Routes.HOME){
                    HomeScreen()
                }
                composable(route=Routes.REGISTER){
                    RegisterScreen(
                        onClickBack = {
                            navControl.popBackStack()
                        },
                        onClickNext = {
                            navControl.navigate(Routes.REGISTER_STEP_2)
                        }
                    )
                }
                composable(route= Routes.REGISTER_STEP_2){
                    RegisterStep2Screen(
                        onClickBack = {
                            navControl.popBackStack()
                        },
                        onClickNext = {
                            navControl.navigate(Routes.REGISTER_STEP_3)
                        }
                    )
                }
                composable(route=Routes.REGISTER_STEP_3){
                    RegisterStep3Screen(
                        onClickBack = {
                            navControl.popBackStack()
                        },
                        onClickNext = {
                            navControl.navigate(Routes.HOME)
                        }
                    )
                }
            }
        }
    }
}

object Routes{
    const val LOGIN = "login"
    const val HOME = "home"
    const val REGISTER = "register"
    const val REGISTER_STEP_2 = "register_step_2"
    const val REGISTER_STEP_3 = "register_step_3"
}