package com.example.bigproj.presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bigproj.R
import com.example.bigproj.presentation.Screen.state.RegisterScreenEvent
import com.example.bigproj.presentation.Screen.state.RegisterScreenState
import com.example.bigproj.presentation.Screen.viewmodel.RegisterScreenViewModel
import com.example.bigproj.presentation.navigation.Screen
import perfetto.protos.RegisterSqlPackageArgs

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    val viewModel = viewModel<RegisterScreenViewModel>()
    RegisterView(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun RegisterView(
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit,
    onNavigateTo: (Screen) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var doctorToken by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("ПАЦИЕНТ") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Заголовок
        Text(
            text = "Регистрация",
            fontSize = 24.sp,

            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .padding(top = 100.dp)
                .align(Alignment.Start)

        )

        Text(
            text = "Создайте аккаунт для работы в системе",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.Start)
        )

        // Поле имени
        Text(
            text = "Имя",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                onEvent(RegisterScreenEvent.NameUpdated(it))
            },
            placeholder = { Text("Введите имя", color = Color.Gray, fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF006FFD),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
            ),
            textStyle = TextStyle(fontSize = 14.sp)
        )

        // Поле email
        Text(
            text = "Электронная почта",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = {
                onEvent(RegisterScreenEvent.EmailUpdated(it))
            },
            placeholder = { Text("Введите электронную почту", color = Color.Gray, fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF006FFD),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
            ),
            textStyle = TextStyle(fontSize = 14.sp)
        )


        // Поле для токена врача
        if (selectedRole == "ВРАЧ") {
            Text(
                text = "Токен врача",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 6.dp)
            )

            OutlinedTextField(
                value = doctorToken,
                onValueChange = { doctorToken = it },
                placeholder = { Text("Введите токен врача", color = Color.Gray, fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF006FFD),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
                ),
                textStyle = TextStyle(fontSize = 14.sp)
            )
        }

        // Чекбокс согласия
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buildAnnotatedString {
                    append("Я изучил и согласен с ")
                    withStyle(style = SpanStyle(color = Color(0xFF006FFD), fontWeight = FontWeight.Medium)) {
                        append("Условиями работы в системе")
                    }
                    append(" и с ")
                    withStyle(style = SpanStyle(color = Color(0xFF006FFD), fontWeight = FontWeight.Medium)) {
                        append("Порядком хранения данных")
                    }
                    append(".")
                },
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable {

                    }
            )
        }

        // Выбор роли
        Text(
            text = "Ваша роль?",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RoleCard(
                text = "ПАЦИЕНТ",
                isSelected = selectedRole == "ПАЦИЕНТ",
                onClick = { selectedRole = "ПАЦИЕНТ" },
                modifier = Modifier.width(90.dp)
            )

            RoleCard(
                text = "ВРАЧ",
                isSelected = selectedRole == "ВРАЧ",
                onClick = { selectedRole = "ВРАЧ" },
                modifier = Modifier.width(90.dp)
            )
        }

        // Кнопка регистрации
        Button(
            onClick = { onNavigateTo(Screen.Verification) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006FFD))
        ) {
            Text(
                text = "Зарегистрироваться",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier.padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Уже есть аккаунт? ",
            )
            Text(
                text = "Войти",
                color = Color(0xFF006FFD),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    onNavigateTo(Screen.Login)
                }
            )
        }

    }
}

// Карточка для выбора роли
@Composable
fun RoleCard(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(24.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF006FFD) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = if (isSelected) null else CardDefaults.outlinedCardBorder()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 6.dp,
                    bottom = 6.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 0.1.sp
            )
        }
    }
}