package com.seuvigie.data.repository

import android.util.Log
import com.seuvigie.data.local.UserLocalDataSource
import com.seuvigie.data.mapper.toDomain
import com.seuvigie.data.mapper.toEntity
import com.seuvigie.data.remote.AuthRemoteDataSource
import com.seuvigie.data.remote.UserRemoteDataSource
import com.seuvigie.domain.model.LoginUser
import com.seuvigie.domain.model.User
import com.seuvigie.domain.model.UserCreation
import com.seuvigie.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authRemote: AuthRemoteDataSource,
    private val userRemote: UserRemoteDataSource,
    private val userLocal: UserLocalDataSource
) : UserRepository {

    override suspend fun createUser(user: UserCreation): Result<User> {
        return try {

            Log.d("UserRepo", "Iniciando registro no Firebase")

            // 1️⃣ cria no FirebaseAuth
            val uid = authRemote.register(user.email, user.password)

            Log.d("UserRepo", "Firebase registrou UID: $uid")

            // 2️⃣ cria entidade completa
            val userEntity = user.toEntity(uid)

            Log.d("UserRepo", "Salvando no Firestore")

            // 3️⃣ salva no Firestore
            userRemote.saveUser(userEntity)

            Log.d("UserRepo", "Salvando no Room")


            // 4️⃣ salva local
            userLocal.insert(userEntity)

            Log.d("UserRepo", "Usuário salvo localmente com sucesso")

            Result.success(userEntity.toDomain())

        } catch (e: Exception) {
            Log.e("UserRepo", "Erro ao criar usuário", e)

            Result.failure(e)
        }
    }

    override suspend fun authUser(user: LoginUser): Result<User> {
        return try {

            Log.d("UserRepo", "Iniciando login")

            // 1️⃣ login
            val uid = authRemote.login(user.email, user.password)

            Log.d("UserRepo", "Login ok. UID: $uid")

            // 2️⃣ busca perfil remoto
            val remoteUser = userRemote.getUserProfile(uid)

            Log.d("UserRepo", "Perfil buscado no Firestore: $remoteUser")

            // 3️⃣ salva local
            userLocal.insert(remoteUser)
            Log.d("UserRepo", "Usuário salvo localmente")


            Result.success(remoteUser.toDomain())

        } catch (e: Exception) {
            Log.e("UserRepo", "Erro no login", e)
            Result.failure(e)
        }
    }
}