DELIMITER ;;
CREATE TRIGGER shiroDb.tri_update
    before UPDATE
    ON shiroDb.subject_info
    FOR EACH ROW
begin
    set NEW.update_time = now(),NEW.version = OLD.version + 1;
end
;;
DELIMITER ;