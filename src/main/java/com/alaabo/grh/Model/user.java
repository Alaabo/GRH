package com.alaabo.grh.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class user {
    private String arme , matricule , nome , prenome , grade , affectation , idn , tuttel , category ,position , pob , adress, willaya , sex , situationFamilial ;
    private Date DOB , FonctionDepuitLe , DateRecruttement , GradeDepuis , Arrivage , Quitter , CNDelveryDate, PassportDelivery , PassportExpiration ;
    private int NIN , NbrEnfant , CIN , NSS , ExtraitNaissance , CCP , CartVote , passport ;



}
