package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Client
import com.example.springsecurityold.domain.repo.ClientRepository
import com.example.springsecurityold.dto.ClientDto
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val userService: UserService
) {
    fun findCurrentClient(principal: Principal) = userService.findUserByUsername(principal.name)
        .let { clientRepository.getClientByUserId(it.id!!) }
        ?: throw NotFoundException()

    fun findAllClients(): MutableList<Client> = clientRepository.findAll()

    fun updateClientCard(principal: Principal, dto: ClientDto): Client = findCurrentClient(principal)
        .let { client ->
            if (client.phone != dto.phone && dto.phone != null) {
                client.phone = dto.phone
            }

            if (client.fullName != dto.fullName && dto.fullName != null) {
                client.fullName = dto.fullName
            }
            return client
        }

    fun saveClient(client: Client) = clientRepository.save(client)
}