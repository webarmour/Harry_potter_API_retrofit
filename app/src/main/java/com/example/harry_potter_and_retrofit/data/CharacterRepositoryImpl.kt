package com.example.harry_potter_and_retrofit.data


import com.example.harry_potter_and_retrofit.data.localdb.dao.Dao
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.data.network.RetrofitInstance
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val mapper: CharacterModelMapper,
    private val charactersDao: Dao,
) : CharacterRepository {


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


