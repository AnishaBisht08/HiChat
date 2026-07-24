package com.example.hichat.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hichat.R
import com.example.hichat.ui.theme.PrimaryBlue
import com.example.hichat.ui.theme.TextPrimary
import com.example.hichat.ui.theme.TextSecondary
import com.example.hichat.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))


        Text(
            text = "Welcome Back 👋",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Sign in to continue chatting",
            fontSize = 15.sp,
            color = TextSecondary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Email")
            },
            leadingIcon = {
                Icon(Icons.Default.Email, null,tint = TextSecondary)
            },
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8FAFC),
                unfocusedContainerColor = Color(0xFFF8FAFC),

                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = TextSecondary,

                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,

                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = TextSecondary
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Password")
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, null, tint = TextSecondary)
            },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8FAFC),
                unfocusedContainerColor = Color(0xFFF8FAFC),

                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = TextSecondary,

                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,

                cursorColor = PrimaryBlue,
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = TextSecondary
            )
        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = "Forgot Password?",
            color = PrimaryBlue,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium
        )


        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {

                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                viewModel.loginUser(
                    email,
                    password
                ) { success, error ->

                    if (success) {
                        onLoginClick()
                    } else {
                        Toast.makeText(
                            context,
                            error ?: "Login failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            },
            enabled = !viewModel.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue
            )
        ) {

            if (viewModel.isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = PrimaryBlue,
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "Sign In",
                    fontSize = 17.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )

            Text(
                "  OR  ",
                color = TextSecondary,
                fontSize = 13.sp
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(22.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(
                1.dp,
                Color(0xFFE5E7EB)
            )
        ) {

            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Continue with Google",
                color = TextPrimary,
                fontWeight = FontWeight.Medium
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(vertical = 14.dp)
        ) {

            Text(
                "Don't have an account? ",
                color = TextSecondary
            )

            Text(
                text = "Sign Up",
                color = PrimaryBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onSignUpClick()
                }
            )

        }

    }
}