package com.Aniramki.FlashCash.repository;

import com.Aniramki.FlashCash.model.Transfer;
import com.Aniramki.FlashCash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Repository
public interface BarChartRepository extends JpaRepository<User, Integer> {

    @Procedure(procedureName = "spFriendAmountMonth")
    List<Map<String, Object>> callFriendAmountMonth(int userFromId, Integer yearTransfer);

}
