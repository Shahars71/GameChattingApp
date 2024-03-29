package com.example.gamechattingapp.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamechattingapp.R;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void registerUserData() {
            EditText userEmail = this.findViewById(R.id.emailInput);
            EditText userPass = this.findViewById(R.id.passwordInput);
            EditText userName = this.findViewById(R.id.nameinput);
            EditText userPhone = this.findViewById(R.id.phone_input);
            EditText userAge = this.findViewById(R.id.age_input);


            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference("users").child(userName.getText().toString());

            User user1 = new User(userEmail.getText().toString(),userPass.getText().toString(),userPhone.getText().toString(),userAge.getText().toString(),userName.getText().toString());
            String email=userEmail.getText().toString();

            String password=userPass.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign i n success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                DatabaseReference myRef = database.getReference("users").child(firebaseUser.getUid());
                                myRef.setValue(user1);
                                Toast.makeText(MainActivity.this, "User created successfully!.",
                                        Toast.LENGTH_SHORT).show();


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        public void onSignIn(View v) {
                EditText userEmail = this.findViewById(R.id.emailInput);
                EditText userPass = this.findViewById(R.id.passwordInput);


                String email = userEmail.getText().toString();
                String password = userPass.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    fetchUserDetails(user);
                                    //updateUI(user);
                                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_shopFragment);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }

            private void fetchUserDetails(FirebaseUser firebaseUser) {
                    if (firebaseUser != null) {
                        String userId = firebaseUser.getUid();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
                        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();//a@a.com
                        //String email = FirebaseDatabase.getInstance().getReference("users").child(userId).child("email");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override

                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.d("FirebaseDebug", "onDataChange invoked.");
                                User user = dataSnapshot.getValue(User.class);
                                if (user != null) {
                                    currentUser = user;
                                    usernameTextView.setText(currentUser.getName());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d("FirebaseDebug", "onCancelled invoked.");
                                // Handle possible errors
                            }
                        });
                    }
                }
}