package com.example.harry_potter_and_retrofit.data


import android.app.Application
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.data.network.RetrofitInstance
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    application: Application,
    private val mapper : CharacterModelMapper

) : CharacterRepository {

    private val charactersDao = CharacterDatabase
        .getInstance(application).getCharacterDao()

    //Network

    override suspend fun getCharactersFromNetwork(): List<CharacterItem> {

        return mapper.listDtoTolistModel(RetrofitInstance.searchCharactersApi.getCharacters())
    }

    override suspend fun getCharacterByIdFromNetwork(id: Int): CharacterItem {
        return mapper.characterModelDtoToCharacterModel(
            RetrofitInstance.searchCharactersApi.getCharactersById(
                id
            )
        )
    }

    override suspend fun saveCharacterToLocalDb(characterItem: CharacterItem) =
        charactersDao.insertCharacterItem(mapper.mapModelToDbModel(characterItem))


    override suspend fun saveCharacterListToLocalDb(characterList: List<CharacterItem>) =
        charactersDao.insertCharacterList(mapper.mapListModelToDtoListModel(characterList))

    override suspend fun getCharacterFromLocalDb(id: Int): CharacterItem {
      return mapper.mapDbModelToModel(charactersDao.getCharacterById(id))
    }

    override suspend fun getCharacterListFromLocalDb(): List<CharacterItem> {
        return mapper.mapDbListModelToListModel(charactersDao.getAllCharacters())
    }


}


