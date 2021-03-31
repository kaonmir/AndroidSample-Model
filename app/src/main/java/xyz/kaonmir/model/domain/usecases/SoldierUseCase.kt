package xyz.kaonmir.model.domain.usecases

import xyz.kaonmir.model.data.entities.NameModel
import xyz.kaonmir.model.data.entities.SoldierModel
import xyz.kaonmir.model.data.repositories.SoldierRepository
import xyz.kaonmir.model.domain.exceptions.SoldierExistsException
import xyz.kaonmir.model.domain.exceptions.SoldierNotFoundException
import xyz.kaonmir.model.domain.exceptions.SoldierValidationException
import java.util.regex.Matcher
import java.util.regex.Pattern

class SoldierUseCase(private val soldierRepository: SoldierRepository) {
    private fun validateSoldierMil(name: String, serialNumber: String): Matcher {
        val nameRegex = " *([a-zA-Z]+) *, *([a-zA-Z ]+) *(?:\\. *([a-zA-Z]+))? *" // military style
        return Pattern.compile(nameRegex).matcher(name)
    }

    private fun validateSoldierCiv(name: String, serialNumber: String): Matcher {
        val nameRegex = " *([a-zA-Z]+) +([a-zA-Z]+)? +([a-zA-Z] *)"
        return Pattern.compile(nameRegex).matcher(name)
    }

    suspend fun insertSoldier(name: String, serialNumber: String) {
        // todo (exception handling on coroutine)
//        if (soldierRepository.getSoldierBySerialNumber(serialNumber) != null) throw(SoldierExistsException())

        val matcherMil = validateSoldierMil(name, serialNumber)
        val matcherCiv = validateSoldierCiv(name, serialNumber)

        when {
            matcherMil.matches() -> {
                val newName = NameModel(matcherMil.group(2)!!, matcherMil.group(3), matcherMil.group(1)!!)
                soldierRepository.insert(SoldierModel(serialNumber, newName))
            }
            matcherCiv.matches() -> {
                val newName = NameModel(matcherMil.group(1)!!, matcherMil.group(2), matcherMil.group(3)!!)
                soldierRepository.insert(SoldierModel(serialNumber, newName))
            }
//            else -> throw(SoldierValidationException())
        }
   }

    fun getAllSoldiers() = soldierRepository.getAll()
    suspend fun deleteSoldiers(inputSoldier: SoldierModel) = soldierRepository.delete(inputSoldier)
}