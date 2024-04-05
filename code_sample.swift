        .onAppear() {
            Auth.auth().addStateDidChangeListener { auth, user  in
                if user != nil {
                    isUserLogged.toggle();
                }
            }
        }

// up is changing view code

    var content: some View {

    }

    // content var to put Vstack inside
