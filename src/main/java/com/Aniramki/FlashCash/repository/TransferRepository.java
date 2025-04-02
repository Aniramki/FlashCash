package com.Aniramki.FlashCash.repository;

import com.Aniramki.FlashCash.model.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransferRepository  extends CrudRepository<Transfer, Long> {
    //attention HQL on pointe sur l objets du code et pas sur la base en direct
    //au besoin si on veux faire du vrai sql rajoiter (nativeQuery = true) apres les guillements


    public Optional<Transfer> findTransferByDate(String transfer);

    @Query("SELECT t FROM Transfer t WHERE t.from.id= :id")
    List<Transfer> findTransferByUser(Integer id);


}
