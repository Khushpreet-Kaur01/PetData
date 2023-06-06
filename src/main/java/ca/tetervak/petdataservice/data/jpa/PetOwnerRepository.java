package ca.tetervak.petdataservice.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200/")
@RepositoryRestResource(
        path = "owners",
        collectionResourceRel = "owners",
        itemResourceRel = "owner"
)
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {
}
